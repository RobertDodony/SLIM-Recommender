Adatbázissal kapcsolatos tudnivalók:

A program MySQL adatbázist használ (én a MySQL Workbench 8.0 CE -t használtam).
1. Szükséges egy MySQL Connection-t létrehozni, ehhez egy felhasználónév és egy jelszó is tartozik.
2. Bejelentkezés után az "ajanlorendszer_adatok.sql" script futtatásával létrejön és adatokkal is feltöltődik egy "bookshop" nevű adatbázis.
3. A Spring Boot applikáción belül az src/main/resources mappában található application.properties fájlba kell megadni a következőket:
	spring.datasource.url=jdbc:mysql://{kapcsolat/adatbázis}?useSSL=false&serverTimezone=UTC
	spring.datasource.username={felhasználónév}
	spring.datasource.password={jelszó}
	
		Például: 
			spring.datasource.url=jdbc:mysql://localhost:3306/bookshop?useSSL=false&serverTimezone=UTC
			spring.datasource.username=springstudent
			spring.datasource.password=springstudent
			
	A kitöltendő részek a {}-eken belül vannak.
4. Ezek után az applikáció eléri az adatbázist.

Maven:

A program futtatása előtt egy maven build szükséges, amely behúzza a megfelelő dependenciákat.

Program futtatása:

A "ProjectApplicaton.java" nevű fájl futtatásával indítható el az applikáció.

