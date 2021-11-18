# Game Recomendation Manager ![Alt text](https://img.shields.io/badge/GRM-Game%20Recommendation%20Manager-green?style=for-the-badge&logo=appveyor) 

GRM is a barebones project to better understand different design patterns and how to use GitHub as a team.\

Made for ICS 370 at Metropolitan State University. 

![Alt text](/lib/images/GRM_Quiz.png?raw=true "GRM Quiz")

## Installation
Developed for Windows and Linux and used only in local environments. A standalone jar was never made so you will have to run from your preferred IDE.
### Windows
 #### Software needed
  * [MySQL Workbench](https://www.mysql.com/products/workbench/) to install and configure the database.
  * [Xampp](https://www.apachefriends.org/index.html) to run the local server (Many other options).
#### Steps
1. Add libraries to IDE from the lib folder.
2. Add load_database.sql to MySQL workbench from the lib folder to install database.
3. Launch Xampp and run the local MySQL server.
4. Run program from your IDE and test with the user info ( user | p@ssword ) or create your own user. 
  

### Linux
```bash
sudo apt-get install mysql-server
```

## License
[MIT](https://choosealicense.com/licenses/mit/)
