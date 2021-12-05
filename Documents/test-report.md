# Teszt jegyzőkönyv

|Funkció leírása|Vizsgálat módja|Elvárt eredmény|Megfelelőség|
|---------------|---------------|---------------|------------|
|Megfelelő jelszó generálása             |Automatikus tesztelés   |A paramétereknek megfelelő jelszó generálása                      | :heavy_check_mark:
|Regisztráció túl hosszú felhasználónévvel             |Automatikus tesztelés   |Túl hosszú felhasználónév esetén sikertelen regisztráció, hibaüzenet után a program fut tovább                      | :heavy_check_mark:
|Regisztráció túl hosszú jelszóval             |Automatikus tesztelés   |Túl hosszú jelszó esetén sikertelen regisztráció, hibaüzenet után a program fut tovább                      | :heavy_check_mark:
|Regisztráció érvényes adatokkal             |Manuális tesztelés, új fiók regisztrálása   |Sikeres regisztráció                         | :heavy_check_mark:
|Regisztráció hiányos adatokkal             |Manuális tesztelés, új fiók regisztrálása   |Hiányos adatok esetén nem történik regisztráció, hibaüzenet után a program fut tovább                         | :heavy_check_mark:
|Regisztráció foglalt névvel             |Manuális tesztelés, új fiók regisztrálása   |Nem történik regisztráció foglalt felhasználónév esetén, hibaüzenet után a program fut tovább                         | :heavy_check_mark:
|Regisztráció túl hosszú jelszóval             |Manuális tesztelés, új fiók regisztrálása   |Nem történik regisztráció ha a jelszó hosszabb a megengedettnél, ibaüzenet után a program fut tovább                          | :heavy_check_mark:
|Bejelentkezés érvényes adatokkal             |Manuális bejelentkezés   |Sikeres belépés                         | :heavy_check_mark:
|Bejelentkezés hiányos adatokkal             |Manuális bejelentkezés   |Hiányos adatok esetén nem történik bejelentkezés, hibaüzenet után a program fut tovább                         | :heavy_check_mark:
|Bejelentkezés hibás adatokkal             |Manuális bejelentkezés   |Hibás adatok esetén nem történik bejelentkezés, hibaüzenet után a program fut tovább                         | :heavy_check_mark:
|Profil tárolása teljesen kitöltött mezőkkel|Manuális tesztelés, új profil hozzáadása|Sikeres adatmentés| :heavy_check_mark:
|Profil tárolása részlegesen kitöltött mezőkkel|Manuális tesztelés, új profil hozzáadása|Bármely mező(k) üresen hagyása esetén nem történik mentés, hibaüzenet kerül kiírásra| :heavy_check_mark:
|Létező profil frissítése teljesen kitöltött mezőkkel|Manuális tesztelés, profil frissítése|Sikeres frissítés| :heavy_check_mark:
|Létező profil frissítése részlegesen kitöltött mezőkkel|Manuális tesztelés, profil frissítése|Bármely mező(k) üresen hagyása esetén nem történik frissítés, hibaüzenet kerül kiírásra| :heavy_check_mark:
|Nem létező profil frissítése teljesen kitöltött mezőkkel|Manuális tesztelés, profil frissítése|Nem létező profil nem frissíthető, hibaüzenet kerül kiírásra| :heavy_check_mark:
|Nem létező profil frissítése részlegesen kitöltött mezőkkel|Manuális tesztelés, profil frissítése|Bármely mező(k) üresen hagyása esetén nem történik frissítés, hibaüzenet kerül kiírásra| :heavy_check_mark:
|Profillista folyamatos naprakészsége|Manuális tesztelés, új profil hozzáadása|A programban való navigáció nélkül is azonnal elérhető a frissen hozzáadott profil a legördülő listából| :heavy_check_mark:
|Profil kiválasztása|Manuális tesztelés, profil kiválasztása|Kiválasztott profil esetén gombnyomás után a felhasználónév kiírásra kerül, a jelszó pedig vágólapra másolódik| :heavy_check_mark:
|Profil kiválasztása|Manuális tesztelés, a kezdetben üres elem kiválasztása|Nem történik semmi, a futás nem áll meg| :heavy_check_mark:
