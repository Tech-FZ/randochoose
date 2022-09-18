# Randochoose
A random generator written in Java.

## Mirrors

GitHub: https://github.com/Tech-FZ/randochoose

Codeberg: https://codeberg.org/lucien-rowan/randochoose

## We're Using GitHub Under Protest

This project is currently hosted on GitHub.  This is not ideal; GitHub is a
proprietary, trade-secret system that is not Free and Open Souce Software
(FOSS).  We are deeply concerned about using a proprietary system like GitHub
to develop our FOSS project.  We have an
[open issue](https://github.com/Tech-FZ/randochoose/issues/12) where the
project contributors are actively discussing how we can move away from GitHub
in the long term.  We urge you to read about the
[Give up GitHub](https://GiveUpGitHub.org) campaign from
[the Software Freedom Conservancy](https://sfconservancy.org) to understand
some of the reasons why GitHub is not a good place to host FOSS projects.

If you are a contributor who personally has already quit using GitHub, please 
contact me at lucien-rowan#1916 on Discord so we can find a solution together.

Any use of this project's code by GitHub Copilot, past or present, is done
without our permission.  We do not consent to GitHub's use of this project's
code in Copilot.

![Logo of the GiveUpGitHub campaign](https://sfconservancy.org/img/GiveUpGitHub.png)

# System requirements
OS: Windows 7 SP1¹ or newer (amd64); Windows Server 2012 or newer (amd64); macOS 10.11² or newer (Intel, Apple Silicon); Solaris 11.3³ or newer (SPARC); RHEL 6 (amd64), Oracle Linux 6 (amd64, Aarch64⁴), Suse Linux Enterprise Server 12 (amd64), Ubuntu Linux 18.04 LTS (amd64), distributions based on one of those or newer

Java: Java 11⁵ or newer

Processor: Intel Core 2 Duo @ 2.4 GHz or equivalent

RAM: 4 GB (6 GB if running Windows 11)

HDD: 2 MB free disk space (really small, right?)

¹ Windows 7 is no longer supported by Microsoft. Support requests from Windows 7 users will not be prioritized.

² The creator of this program hasn't got a Mac and therefore macOS support requests will not be prioritized.

³ The creator must emulate the architecture for Solaris and even then - eventually isn't able to get Java binaries for the platform legally. Support requests will not be prioritized.

⁴ Only Oracle Linux 7.6+ can handle this program on Aarch64 machines. Additionally, support requests will be resolved very slowly due to emulation being required.

⁵ The official Oracle binaries are not free for anything but development and testing purposes. Using the Adoptium Temurin binaries is recommended. You can get them here: https://adoptium.net/de/

## Versioning scheme

Updated: 18th September, 2022 with 2022.9.0

A versioning scheme can be difficult. To help, I decided to explain. Generally, it is listed as yyyy.m.r with yyyy being the year a version was released, m being the month a version was released and r being the release number within a month.

### Examples

| Version | Interprete as |
| ------- | ------------- |
| 2022.9.0 | The first release in September 2022 |
| 2039.11.7 | The eigth release in November 2039 |
| 2012.2.19 | The 20th release in February 2012 |

## Installation
Just download the JAR file and run the program after installing Java. Easy, right?

## Building
Just download the source code and run Eclipse. I recommend beginning a new project and copy the source code into new files. Then you can run it. If it works, right click on src, then choose Export, expand Java, click on Runnable JAR file, follow the instructions and - done!

## Contribution
You must open a new issue before making contributions. But then, feel free to apply your plans to the program.

## Forking
This program is licensed under MIT. That means you can fork it and make it your own with little restrictions. Isn't that cool?
