package io.tbbc.cf.mod;

import io.quarkus.qute.Location;
import io.quarkus.qute.Template;
import io.tbbc.cf.common.IOHelper;
import io.tbbc.cf.research.IResearchService;
import io.tbbc.cf.research.ResearchEgoProp;
import io.tbbc.cf.research.ResearchService;
import io.tbbc.cf.turret.ITurretService;
import io.tbbc.cf.turret.TurretEgoProps;
import io.tbbc.cf.turret.TurretEgoType;
import io.tbbc.cf.turret.TurretService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static io.tbbc.cf.common.IOHelper.*;

@ApplicationScoped
public class ModService implements IModService {
    public static final int EGO_FACTION_SECTION = 142010;

    public static final String TURRET_MACRO_PATH = "\\assets\\props\\WeaponSystems\\cf\\macros\\";
    public static final String COMPONENT_PATH = "\\assets\\props\\WeaponSystems\\cf\\";
    public static final String ICON_PATH = "\\assets\\fx\\gui\\textures\\upgrades\\";
    public static final String BULLET_MACRO_PATH = "\\assets\\fx\\weaponfx\\cf\\macros\\";
    public static final String EXTENSIONS_PATH = "extensions";
    private static final String MOD_ID = "tbb_customfaction";
    private static final String MOD_NAME = "Custom Faction";
    private static final String MOD_AUTHOR = "TrueBlueBanana";
    private static final String VERSIONS_FOLDER_NAME = "versions";
    private static final String MOD_FOLDER_NAME = "customfaction";

    private static final List<String> TURRET_COMPONENTS = List.of(
            "turret_cf_kha_l_beam_01_mk1", "turret_cf_kha_l_laser_01_mk1",
            "turret_cf_ter_m_laser_01_mk1", "turret_cf_ter_m_laser_02_mk1"
    );

    @Inject
    ITurretService turretService;
    @Inject
    IModInfosService modInfosService;
    @Inject
    IResearchService researchService;

    @Location("content.xml")
    Template contentXmlTemplate;
    @Location("wares.xml")
    Template waresXmlTemplate;
    @Location("icons.xml")
    Template iconsXmlTemplate;
    @Location("macros.xml")
    Template macrosXmlTemplate;
    @Location("turret_macro.xml")
    Template turretMacroTemplate;
    @Location("bullet_macro.xml")
    Template bulletMacroTemplate;
    @Location("lang_0001.xml")
    Template languagesTemplate;
    @Location("setup_blueprints.xml")
    Template setupBlueprintsTemplate;
    @Location("races.xml")
    Template racesTemplate;
    @Location("components.xml")
    Template componentsTemplate;

    @Override
    public void generateNewVersion() {
        ModInfos actualModInfos = modInfosService.getActualModInfos();
        List<TurretEgoProps> turretEgoProps = turretService.getTurretEgoProps();
        List<ResearchEgoProp> research = researchService.getResearchEgoProps();
        File modFolder = generateModFolder(actualModInfos.getEgoVersion());
        generateModFiles(modFolder, actualModInfos, turretEgoProps, research);
        turretService.deployTurrets();
        modInfosService.deployAndGenerateNewVersion();
    }

    private void generateModFiles(File modFolder, ModInfos actualModInfos, List<TurretEgoProps> turretEgoProps,
                                  List<ResearchEgoProp> research) {
        generateContentXml(modFolder, actualModInfos);
        File librariesFolder = createAndGetFolder(new File(modFolder, "libraries"), Behavior.IGNORE);
        generateWaresXml(librariesFolder, turretEgoProps, research);
        generateIconXml(librariesFolder);
        generateRacesXml(librariesFolder);
        File indexFolder = createAndGetFolder(new File(modFolder, "index"), Behavior.IGNORE);
        generateMacroXml(indexFolder, turretEgoProps);
        File assetsFolder = createAndGetFolder(new File(modFolder, "assets"), Behavior.IGNORE);
        generateTurretsMacro(assetsFolder, turretEgoProps);
        generateBulletsMacro(assetsFolder, turretEgoProps);
        copyTurretsIcons(assetsFolder, turretEgoProps);
        File languagesFolder = createAndGetFolder(new File(modFolder, "t"), Behavior.IGNORE);
        generateLanguages(languagesFolder, turretEgoProps, actualModInfos, research);
        File mdFolder = createAndGetFolder(new File(modFolder, "md"), Behavior.IGNORE);
        generateBlueprintScriptFiles(mdFolder, actualModInfos, turretEgoProps, research);
        addNewTurretComponents(modFolder);
    }

    private void generateRacesXml(File librariesFolder) {
        File racesFile = createAndGetFile(new File(librariesFolder, "races.xml"), Behavior.THROW);
        String racesXml = racesTemplate
                .data("factionLabelSection", EGO_FACTION_SECTION)
                .render();
        writeInFile(racesFile, racesXml);
    }

    private void generateIconXml(File librariesFolder) {
        File iconsFile = createAndGetFile(new File(librariesFolder, "icons.xml"), Behavior.THROW);
        String iconsXml = iconsXmlTemplate
                .data("modFolder", "%s\\%s".formatted(EXTENSIONS_PATH, librariesFolder.getParentFile().getName()))
                .data("iconPath", ICON_PATH)
                .render();
        writeInFile(iconsFile, iconsXml);
    }

    private void copyTurretsIcons(File assetsFolder, List<TurretEgoProps> turretEgoProps) {
        File fxFolder = createAndGetFolder(new File(assetsFolder, "fx"), Behavior.IGNORE);
        File guiFolder = createAndGetFolder(new File(fxFolder, "gui"), Behavior.IGNORE);
        File texturesFolder = createAndGetFolder(new File(guiFolder, "textures"), Behavior.IGNORE);
        File upgradesFolder = createAndGetFolder(new File(texturesFolder, "upgrades"), Behavior.IGNORE);
        turretEgoProps.forEach(props -> {
            File file = createAndGetFile(new File(upgradesFolder, "%s.gz".formatted(props.macroName())), Behavior.THROW);
            writeInFile(file, IOHelper.getResourceContent("/icon/%s.gz".formatted(props.skinComponent())));
        });
    }

    private void generateBlueprintScriptFiles(File mdFolder, ModInfos actualModInfos, List<TurretEgoProps> turretEgoProps,
                                              List<ResearchEgoProp> research) {
        File file = createAndGetFile(new File(mdFolder, "setup_blueprints.xml"), Behavior.THROW);
        String setupBlueprintsXml = setupBlueprintsTemplate
                .data("version", actualModInfos.getVersion())
                .data("turrets", turretEgoProps)
                .data("research", research)
                .data("researchNeeded", actualModInfos.getResearchMode().equals(ModInfos.ResearchMode.RESEARCH))
                .render();
        writeInFile(file, setupBlueprintsXml);
    }

    private void generateLanguages(File languagesFolder, List<TurretEgoProps> turretEgoProps, ModInfos actualModInfos,
                                   List<ResearchEgoProp> research) {
        File file = createAndGetFile(new File(languagesFolder, "0001.xml"), Behavior.IGNORE);
        String languagesXml = languagesTemplate
                .data("turrets", turretEgoProps)
                .data("turretLabelSection", TurretService.EGO_TURRET_LABEL_SECTION)
                .data("turretDescriptionSection", TurretService.EGO_TURRET_DESCRIPTION_SECTION)
                .data("factionLabelSection", EGO_FACTION_SECTION)
                .data("factionShortName", actualModInfos.getFactionTrigram())
                .data("researchLabelSection", ResearchService.EGO_RESEARCH_LABEL_SECTION)
                .data("researchDescriptionSection", ResearchService.EGO_RESEARCH_DESCRIPTION_SECTION)
                .data("research", research)
                .render();
        writeInFile(file, languagesXml);
    }

    private void generateBulletsMacro(File assetsFolder, List<TurretEgoProps> turretEgoProps) {
        File fxFolder = createAndGetFolder(new File(assetsFolder, "fx"), Behavior.IGNORE);
        File weaponFxFolder = createAndGetFolder(new File(fxFolder, "weaponfx"), Behavior.IGNORE);
        File cfFolder = createAndGetFolder(new File(weaponFxFolder, "cf"), Behavior.IGNORE);
        File macrosFolder = createAndGetFolder(new File(cfFolder, "macros"), Behavior.IGNORE);
        generateBulletMacros(macrosFolder, turretEgoProps);
    }

    private void generateBulletMacros(File macrosFolder, List<TurretEgoProps> turretEgoProps) {
        // Dot not generate for alias turrets
        turretEgoProps.stream()
                .filter(turret -> turret.turretEgoType().equals(TurretEgoType.MAIN))
                .forEach(props -> {
                    File file = createAndGetFile(new File(macrosFolder, "%s.xml".formatted(props.bulletMacroName())), Behavior.IGNORE);
                    String macroXml = bulletMacroTemplate.data("turret", props).render();
                    writeInFile(file, macroXml);
                });
    }

    private void generateTurretsMacro(File assetsFolder, List<TurretEgoProps> turretEgoProps) {
        File propsFolder = createAndGetFolder(new File(assetsFolder, "props"), Behavior.IGNORE);
        File weaponSystemsFolder = createAndGetFolder(new File(propsFolder, "WeaponSystems"), Behavior.IGNORE);
        File cfFolder = createAndGetFolder(new File(weaponSystemsFolder, "cf"), Behavior.IGNORE);
        File macrosFolder = createAndGetFolder(new File(cfFolder, "macros"), Behavior.IGNORE);
        generateTurretMacros(macrosFolder, turretEgoProps);
    }

    private void addNewTurretComponents(File modFolder) {
        File assetsFolder = createAndGetFolder(new File(modFolder, "assets"), Behavior.IGNORE);
        File propsFolder = createAndGetFolder(new File(assetsFolder, "props"), Behavior.IGNORE);
        File weaponSystemsFolder = createAndGetFolder(new File(propsFolder, "WeaponSystems"), Behavior.IGNORE);
        File cfFolder = createAndGetFolder(new File(weaponSystemsFolder, "cf"), Behavior.IGNORE);
        TURRET_COMPONENTS.forEach(componentName -> addNewTurretComponents(cfFolder, componentName));
        File indexFolder = createAndGetFolder(new File(modFolder, "index"), Behavior.IGNORE);
        genrateComponentsXml(indexFolder);
    }

    private void genrateComponentsXml(File indexFolder) {
        File file = createAndGetFile(new File(indexFolder, "components.xml"), Behavior.THROW);
        String componentsXml = componentsTemplate
                .data("modFolder", "%s\\%s".formatted(EXTENSIONS_PATH, indexFolder.getParentFile().getName()))
                .data("componentPath", COMPONENT_PATH)
                .data("components", TURRET_COMPONENTS)
                .render();
        writeInFile(file, componentsXml);
    }

    private void addNewTurretComponents(File macrosFolder, String componentName) {
        File file = createAndGetFile(new File(macrosFolder, "%s.xml".formatted(componentName)), Behavior.THROW);
        byte[] turretComponentsXml = IOHelper.getResourceContent("/new/turret_comps/%s.xml".formatted(componentName));
        writeInFile(file, turretComponentsXml);
    }

    private void generateTurretMacros(File macrosFolder, List<TurretEgoProps> turretEgoProps) {
        turretEgoProps.forEach(props -> {
            File file = createAndGetFile(new File(macrosFolder, "%s.xml".formatted(props.macroName())), Behavior.THROW);
            String macroXml = turretMacroTemplate.data("turret", props).render();
            writeInFile(file, macroXml);
        });
    }

    private void generateMacroXml(File indexFolder, List<TurretEgoProps> turretEgoProps) {
        File file = createAndGetFile(new File(indexFolder, "macros.xml"), Behavior.THROW);
        String macroXml = macrosXmlTemplate
                .data("modFolder", "%s\\%s".formatted(EXTENSIONS_PATH, indexFolder.getParentFile().getName()))
                .data("macroPath", TURRET_MACRO_PATH)
                .data("macroBulletPath", BULLET_MACRO_PATH)
                .data("turrets", turretEgoProps).render();
        writeInFile(file, macroXml);
    }

    private void generateWaresXml(File modFolder, List<TurretEgoProps> turretEgoProps, List<ResearchEgoProp> research) {
        File file = createAndGetFile(new File(modFolder, "wares.xml"), Behavior.THROW);
        String waresXml = waresXmlTemplate
                .data("turrets", turretEgoProps)
                .data("research", research)
                .render();
        writeInFile(file, waresXml);
    }

    private void generateContentXml(File modFolder, ModInfos actualModInfos) {
        File file = createAndGetFile(new File(modFolder, "content.xml"), Behavior.THROW);
        String contentXml = contentXmlTemplate.data("id", MOD_ID)
                .data("name", MOD_NAME)
                .data("author", MOD_AUTHOR)
                .data("version", actualModInfos.getEgoVersion().egoVersion())
                .data("date", LocalDate.now().format(DateTimeFormatter.ISO_DATE))
                .render();
        writeInFile(file, contentXml);
    }

    private File generateModFolder(ModVersion modVersion) {
        File versionsFolder = createAndGetFolder(new File(VERSIONS_FOLDER_NAME), IOHelper.Behavior.IGNORE);
        return createAndGetFolder(
                new File(versionsFolder, "%s-%s".formatted(MOD_FOLDER_NAME, modVersion.egoVersion())), Behavior.DELETE);
    }
}
