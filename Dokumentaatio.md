# Yleinen kuvaus
Elokuvateatterin varausjärjestelmä, jossa asiakas voi selata elokuvia ja varata haluamansa elokuvan näytöksestä istumapaikan, jos näytöksiä on. Asiakas voi myös perua varauksensa ja tarkastella kaikkia tekemiään varauksia.

Järjestelmän ylläpitäjä voi tarkastella kaikkien asiakkaiden varauksia, lisätä ja poistaa elokuvia, lisätä ja poistaa näytöksiä sekä tarkastella eri näytöksien salikarttoja.
## Kuvaus ohjelman toteutuksesta
### Ohjelma jaettu seuraaviin luokkiin:
User: Kuvaa yleisesti varausjärjestelmän käyttäjää.

Admin: Varausjärjestelmän ylläpitäjä, perii User-luokan.

Asiakas: Varausjärjestelmän asiakas, perii User-luokan.

Sali: Kuvaa salia, jossa näytökset pidetään. Sisältää tiedon istumapaikkojen määrästä.

Elokuva: Sisältää elokuvan tiedot.

Istumapaikka: Sisältää tiedon istumapaikan sijainnista salissa.

Näytös: Sisältää näytettävän elokuvan, salin ja näytösajan. Sisältää myös tiedon kaikista varatuista istumapaikoista. Sisältää myös metodit, jotka asettavat asiakkaan varaukset salikartalle.

Varaus: Asiakkaan tekemä varaus näytökseen. Sisältää tiedon kaikista tiettyä varausta koskevista istumapaikoista.

Varausjärjestelmä: sisältää metodit varausjärjestelmän tietojen käsittelyyn ja muokkaamiseen käyttäjälle näytettävään muotoon.

VarausjärjestelmäIO: Luokka vastaa kaikkien varausjärjestelmän käyttämien tietojen kirjoittamisesta tiedostoihin, sekä niiden lukemisesta tiedostoista.

VarausjärjestelmäUI: Käyttöliittymää mallintava luokka, joka on vastuussa kaikesta käyttäjälle näytettävästä sisällöstä. Ylläpitäjän ja asiakkaan käyttöliittymät eroteltu ohjelman käynnistämisen yhteydessä tapahtuvalla tunnistautumisella.

## Ulkoisten kirjastojen käyttö
Ohjelmassa ei Junitin lisäksi käytetä ulkoisia kirjastoja.


## Ohjeet ohjelman käynnistämiseen
Kloonaa ja käännä projekti


## Ohjeet ohjelman käyttämiseen
Ohjelma ohjeistaa käyttäjää. Ylläpitäjä voi kirjautua syöttämällä käyttäjätunnukseksi ja salasanaksi "admin".

## Työnjako
Molemmat osallistuivat tasapuolisesti metodien kirjoittamiseen. Juhani viimeistellyt.


## Kuvaus AI-työkalujen käytöstä

Ongelmatilanteessa ChatGPT:lta kysytty neuvoa jonkin tietyn tuloksen saamiseksi. [ai.txt](ai.txt)