# Galgenmännchen (Hangman)

Ein klassisches Galgenmännchen-Spiel implementiert in Java mit einer modernen, objekt-orientierten Architektur und erweiterten Spielmodi.

## 🎮 Spielbeschreibung

Galgenmännchen ist ein Wortratespiel, bei dem ein Spieler versucht, ein geheimes Wort zu erraten. Für jeden falschen Versuch wird schrittweise ein "Galgenmännchen" gezeichnet. Das Spiel endet entweder mit dem erfolgreichen Erraten des Wortes oder wenn das Galgenmännchen vollständig gezeichnet ist.

## ✨ Features

- **Drei Spielmodi:**
  - 🤖 **Einzelspieler gegen Computer** - Erraten Sie ein vom Computer generiertes Wort
  - 👥 **Zwei-Spieler-Modus** - Ein Spieler denkt sich ein Wort aus, der andere rät
  - 🧠 **Computer rät** - Der Computer versucht Ihr Wort zu erraten (KI-Modus)
- **Spielfunktionen:**
  - ASCII-Art Galgen-Visualisierung mit 7 Stufen
  - Anzeige bereits geratener Buchstaben
  - Möglichkeit einzelne Buchstaben oder das komplette Wort zu raten
  - Lebensanzeige mit Fehlerzähler
  - Intelligente Computer-KI mit Buchstabenhäufigkeits-Algorithmus
- **Technische Features:**
  - Online-Wortgenerierung via API
  - Lokale Wortliste als Fallback
  - Robuste Eingabevalidierung
  - Wiederholbare Spielrunden

## 🏗 Projektstruktur

```
galgenmaennchen/
├── Galgenmaennchen.java       # Hauptklasse/Einstiegspunkt
├── controller/
│   └── SpielController.java   # Spielablaufsteuerung und Moduswahl
├── model/
│   ├── ComputerRaten.java    # KI-Logik für Computer-Ratemodus
│   ├── Galgen.java           # ASCII-Art und Zustandsverwaltung
│   ├── SpielLogik.java       # Kernspiellogik und Hilfsmethoden
│   ├── SpielModus.java       # Spielmodus-Enumeration
│   ├── SpielRunde.java       # Einzelspielrunden-Verwaltung
│   └── WortAPI.java          # Wortgenerierung und API-Anbindung
└── view/
    ├── SpielAnzeige.java     # Ein-/Ausgabe-Verwaltung
    └── Text.java             # Textkonstanten und Nachrichten
```

## 🚀 Installation und Start

### Voraussetzungen
- Java JDK 11 oder höher
- Internetverbindung (für Online-Wortgenerierung, optional)

### Installation
1. Klonen Sie das Repository:
   ```bash
   git clone [repository-url]
   cd galgenmaennchen
   ```

2. Kompilieren Sie das Projekt:
   ```bash
   javac galgenmaennchen/*.java galgenmaennchen/*/*.java
   ```

3. Starten Sie das Spiel:
   ```bash
   java galgenmaennchen.Galgenmaennchen
   ```

## 🎯 Spielmodi im Detail

### 1. Einzelspieler gegen Computer
- Der Computer wählt ein zufälliges deutsches Wort
- Sie versuchen das Wort durch Buchstaben oder Wort-Eingaben zu erraten
- 7 Fehlversuche sind erlaubt

### 2. Zwei-Spieler-Modus
- Spieler 1 gibt ein Wort ein (wird versteckt eingegeben)
- Spieler 2 versucht das Wort zu erraten
- Ideal für lokales Spiel zu zweit

### 3. Computer rät (KI-Modus)
- **NEU!** Sie denken sich ein Wort aus
- Der Computer versucht Ihr Wort systematisch zu erraten
- Verwendet intelligente Buchstabenhäufigkeits-Algorithmen
- Computer fragt nach Buchstaben basierend auf deutscher Sprachstatistik

## 🎮 Spielablauf

1. **Moduswahl:** Wählen Sie zwischen den drei verfügbaren Modi
2. **Spielvorbereitung:** 
   - Modus 1: Automatische Wortgenerierung
   - Modus 2: Eingabe des zu erratenden Wortes
   - Modus 3: Sie geben Ihr Wort ein
3. **Spielrunde:** Buchstaben raten oder komplettes Wort eingeben
4. **Spielende:** Sieg bei vollständigem Erraten oder Niederlage bei 7 Fehlern
5. **Wiederholung:** Option für weitere Spielrunden

## 🤖 KI-Algorithmus (Computer rät)

Der Computer verwendet eine intelligente Strategie:
- **Buchstabenhäufigkeit:** Startet mit den häufigsten deutschen Buchstaben (E, N, I, S, R...)
- **Adaptive Strategie:** Passt sich an bereits geratene Buchstaben an
- **Systematisches Vorgehen:** Vermeidet Wiederholungen und optimiert Rateversuche

## 📊 Spielstatistiken

- **ASCII-Galgen:** 7 detaillierte Zeichenstufen
- **Fehlerzähler:** Anzeige aktueller Fehlversuche vs. Maximum
- **Buchstabenverfolgung:** Übersicht aller bereits geratenen Buchstaben
- **Wortfortschritt:** Live-Anzeige des Ratestatus

## 🛠 Technische Details

- **Sprache:** Java (JDK 11+)
- **Architektur:** MVC-Pattern (Model-View-Controller)
- **API-Integration:** Random Word API für deutsche Wörter
- **Fallback-System:** Lokale Wortliste bei API-Ausfall
- **Eingabevalidierung:** Robuste Behandlung ungültiger Eingaben
- **Modularität:** Klare Trennung von Spiellogik, Anzeige und Steuerung

## 📁 Klassen-Übersicht

| Klasse | Verantwortlichkeit |
|--------|-------------------|
| `Galgenmaennchen` | Haupteinstieg und Spielkoordination |
| `SpielController` | Moduswahl und Spielinitialisierung |
| `ComputerRaten` | KI-Logik für Computer-Ratemodus |
| `SpielRunde` | Einzelspielrunden-Management |
| `SpielLogik` | Kernspiellogik und Hilfsfunktionen |
| `Galgen` | ASCII-Visualisierung und Zustandsverwaltung |
| `WortAPI` | Online/Offline Wortgenerierung |
| `SpielAnzeige` | Benutzerinteraktion und Ausgaben |
| `Text` | Zentralisierte Textkonstanten |
| `SpielModus` | Spielmodus-Definitionen |

## 🔧 Konfiguration

### Wort-API Anpassung
Die Standard-API kann in `WortAPI.java` geändert werden:
```java
private static final String API_URL = "https://random-word-api.herokuapp.com/word?lang=de&number=1";
```

### Fallback-Wörter erweitern
Lokale Wortliste in `WortAPI.java` anpassen:
```java
private static final String[] DEFAULT_WORTE = {
    "BANANE", "SCHULE", "TISCH", "COMPUTER", "FENSTER", "PROGRAMMIERUNG"
};
```

## 🎨 Beispiel-Ausgabe

```
Aktueller Stand: P R O _ R A M M _ E R U N G
Fehlversuche: 2/7
Bisher geraten: [A, E, G, M, N, O, P, R, U]
 ______
 |    |
 |    O
 |    |
 |
_|___
```

## 🚀 Zukünftige Erweiterungen

- [ ] Schwierigkeitsgrade (verschiedene Wortlängen)
- [ ] Mehrsprachige Unterstützung
- [ ] Grafische Benutzeroberfläche (GUI)
- [ ] Spielstatistiken und Highscores
- [ ] Thematische Wortkategorien
- [ ] Online-Multiplayer-Modus

## 👥 Mitwirkende

- **M. Moldenhauer** - Ursprünglicher Autor und Hauptentwickler

## 📝 Lizenz

Dieses Projekt ist unter der MIT-Lizenz lizenziert.

## 🐛 Bekannte Probleme

- API-Abhängigkeit für Online-Wortgenerierung
- Umlaute werden derzeit nicht unterstützt
- Nur deutsche Wörter verfügbar

## 📞 Support

Bei Fragen oder Problemen erstellen Sie bitte ein Issue im Repository oder kontaktieren Sie den Entwickler.