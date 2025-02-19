package io.tbbc.cf.mod;

public interface IModInfosService {
    ModInfos getActualModInfos();

    ModInfos deployAndGenerateNewVersion();

    void updateInfos(ModInfosUpdate modInfosUpdate);
}
