# PlayerProfiles
 Minecraft plugin that displays player statistics visually. 
 
This is a Minecraft plugin made to enhance the experience of a player on a multiplayer server. When typing in a command OR right clicking on a player, their "profile" will open up with some information. This includes their player name, online status (or the last time they played), their Discord link, age, Minecraft statistics, and more! The player can change their own information for certain statistic (Discord & age). The plugin utilises SQL to store player data, as well as a dependency on PlaceholderAPI. There is a configuration file to make it slighly customizable. 
This is the first plugin and real Java project I have ever made! Enjoy!

## Info: 
**Dependencies:** Placeholder API *(required)*</br>
**API Version:** Spigot API 1.16</br>

## To Use:
### Clicking: 
Right click a player in-game to view their statistics.
Within a profile page, you can click on certain elements for more features (such as copying to clipboard or getting a command suggested). 

### Commands: 
**/profile** - displays your own profile</br>
**/profile [playername]** - displays the profile of specified player</br>
**/profile set (age|discord) <value>** - set your age or Discord handle</br>

### Notes: 
Ensure you have PlaceholderAPI installed. 
Connecting to an SQL database is currently mandatory.</br>
Change the info in the config.yml
