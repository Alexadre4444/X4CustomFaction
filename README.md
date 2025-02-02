# X4 Foundation - Custom Faction - 0.1.0

## Presentation

This project is an external tool that allows the creation of equipment for a custom faction
accessible only to the player.

Currently, this project is limited to creating turrets using non-DLC assets.

Adding a turret is done as follows:

- Creation of the turret
- Customization of the turret (choosing the type, projectile, and component customizations to modify
  the statistics)
- Generation of the mod containing the created turrets

Then, simply install the mod like any other mod.

For some picture, go to the [preview folder](https://github.com/Alexadre4444/X4CustomFaction/tree/main/preview).

> **_NOTE:_**  This tools is tested only with the 7.50 beta version of X4.

## Motivations

After many runs on X4, during the late game, my empire is either composed of ships and equipment from a single faction (
role play) or a mix of my favorite ships and equipment.

I have always found it unfortunate that there is no customization for ships and equipment to have a "player" faction.

My dream: A ship and equipment editor directly in X4.
The reality: External tools to create mods with the custom equipment.

## Usage

__The tool requires Java 21 or above to run.__

* Download the latest .jar version in [release page](https://github.com/Alexadre4444/X4CustomFaction/releases)
* Launch a powershell or a terminal in the folder and run the following command:

```shell
java -jar X4CustomFaction-0.1.0.jar
```

> **_NOTE:_** Tool data are stored in the folder where the .jar is located.

* Access the tool at [http://localhost:8080](http://localhost:8080)

The tool is composed of 2 page at the moment:

* Home page: Summary and access to mod settings and new version generation
* Turret page: Creation and customization of turrets

Once a turret is created, go back to the home page and click on the "Generate new version" button.

If you create new turrets or modify existing ones, you must generate a new version of the mod.
Install the new version and delete the old one. The save will then take the changes into account.

> **_NOTE:_** The faction trigram can be changed in the mod settings next to the "Generate new version" button.

## Issues and suggestions

If you have any issues or suggestions, please open an issue in
the [issue page](https://github.com/Alexadre4444/X4CustomFaction/issues).

## Roadmap

Currently, this tool is a proof of concept. Depending on my availability, desires, and feedback, the project is expected
to evolve.
Currently, it is certain that future versions will not be backward compatible.

The "short-term" planned features are as follows:

* Addition of turrets and projectiles from DLCs
* Improvement of existing "customizers"
* Addition of area damage and other special effects (notably Boron)
* Addition of a system requiring turrets to be unlocked in-game (probably through research)

The "medium-term" planned features are as follows:

* Addition of weapons
* Addition of shields
* Addition of engines

## Development

This project uses Quarkus, the Supersonic Subatomic Java Framework with the Quinoa extension.

## Running the instance in dev mode

You can run your instance in dev mode that enables live coding using:

```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the instance

Execute the following command:

```shell script
./mvnw package -D quarkus.package.jar.type=uber-jar
```

The instance, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.