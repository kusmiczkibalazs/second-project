# Jelszó menedzser követelmény specifikáció

## 1. Jelenlegi helyzet
Cégünk dolgozóinak mindennapi munkavégzésük során számos jelszóval védett platformra kell bejelentkezniük különböző felhasználói fiókokkal. A rendszereinkben különböző szintű jogosultságokat vezettünk be, amellyekkel más beállításokat érhetnek el az egyes felhasználók. Viszont a felső szintű informatikai munkatársaknak szükséges lehet minden szinthez való belépési jelszavakra a gépek megfelelő tesztelésére, emiatt akár 10-15 jelszót is meg kéne jegyezniük, azt is észben tartva, melyik szinthez tartozik. A könnyebb megjegyezhetőség érdekében többen öntapadós cetlikre írják a jelszavakat, vagy folyamatosan bejelentkezve maradnak. Ebből azonban már többször is biztonsági problémák, visszaélések adódtak. Előfordult, hogy a cetlin található jelszót nem csak a jogos tulajdonosa használta.  
A cég rendszereinél megkövetelünk meghatározott időközönkénti jelszócserét a biztonság továbbnövelése érdekében. Azonban az új jelszót maguknak a felhasználóknak kell kitalálniuk, amikhez sokszor felhasználnak személyes adatokat is, vagy jól ismert kombinációkat, hogy könnyebb legyen megjegyezniük. Viszont ezzel megkönnyítik egy esetleges támadó dolgát, hiszen csökken a feltöréshez szükséges idő, veszélyezetetve a cég titkos, belsős adatait.


## 2. Vágyálom rendszer
Szeretnénk olyan szoftveres megoldást, mellyel alkalmazottaink biztonságosan és kényelmesen, egyetlen fiók használatával tárolhatják, hívhatják vissza jelszavaikat megakadályozva ezzel a korábbi problémákat. Továbbá a szoftver segítsen az időszakonkénti jelszócseréknél kellőképpen biztonságos jelszavakat generálni.


## 3. Jelenlegi üzleti folymatok
3.1 Nagy számú jelszó kezelése  
3.1.1 Jelszavak feljegyzése => monitorra ragasztott öntapadós cetli  
3.1.2 Bejelentkezés megkönnyítése => jelszó megjegyeztetése a rendszerrel  
  
3.2 Jelszavak cseréje  
3.2.1 Időnkénti előírt jelszócsere => Meglévő jelszavakon való minimális változtatás


## 4. Igényelt üzleti folyamatok
4.1 Nagy számú jelszó kezelése  
4.1.1 Jelszavak biztonságos eltárolása => Felhasználóhoz kötött rendszerben való titkosított tárolás  
  
4.2 Tárolt jelszavak felhasználása  
4.2.1 Lekódolt jelszavak visszahívása => Akár a jelszó tényleges megjelenítése nélküli vágólapra másolás gomb segítségével

4.3 Jelszavak cseréje  
4.3.1 Jelszavak generálása megadott paraméterek alapján => Generálás egy egyszerű felhasználói felület segítségével, melynek eredménye egy vágólapra másolható biztonságos jelszó

## 5. Rendszerre vonatkozó szabályok
5.1 Platformfüggetlenség  
5.2 Adatok tárolása relációs adatbázisban  
5.3 Letisztult felhasználói felület  
5.4 Megbízható működés  
5.5 Parancsok gyors, hiba nélküli végrehajtása  
5.6 Egymástól lényegesen eltérő biztonságos jelszavak generálása


## 6. Követelménylista
|ID|Modul|Kifejtés|
|--|-----|--------|
|K01|Átlátható|Könnyen kezelhető rendszer
|K02|Felhasználóbarát megjelenés|Grafikus felület nyomógombokkal. Átlátható, egyértelmű menüpontok
|K03|Időhatékonyság|Parancsok gyors végrehajtása
|K04|Rendelkezésre állás|Fontos jelszavak kerülnek tárolásra, melyek lehetséges, hogy kizárólag ebben a programban lesznek elmentve. Nem megengedhető, hogy egy dolgozó a program elérhetetlensége miatt ne férhessen hozzá egy fiókjához
|K05|Bizalmasság|A munkakörnyezet nem biztosítja, hogy a dolgozók ne láthassanak rá egymás kijelzőjére. A jelszavak ne kerüljenek kiírásra a képernyőn
|K06|Biztonság|A tárolt jelszavakhoz csak az azokhoz jogosult felhasználó férhet hozzá


## 7. Fogalomszótár
Platformfüggetlenség:
>A platformfüggetlenség vagy többplatformosság, illetve multi-platform fogalma olyan számítógépes programokra, operációs rendszerekre, programozási nyelvekre vagy más számítógépes szoftverekre és implementációikra vonatkozik, amelyek több számítógépes platformon képesek működni. ([*Forrás*](https://www.mimi.hu/informatika/platformfuggetlenseg.html))
  
Relációs adatbázis:
>Relációs adatbázisnak nevezzük a relációs adatmodell elvén létrehozott adatok összességét, a relációs adatmodell fogalomrendszerében meghatározott ún. relációk egy véges halmazát. Relációs adatbázisokat relációsadatbázis-kezelőkkel hozhatunk létre, szerkeszthetünk és törölhetünk. ([*Forrás*](https://hu.wikipedia.org/wiki/Rel%C3%A1ci%C3%B3s_adatb%C3%A1zis))
