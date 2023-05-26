-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Erstellungszeit: 26. Mai 2023 um 11:38
-- Server-Version: 10.4.27-MariaDB
-- PHP-Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `fussbalmanag`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `mannschaft`
--

CREATE TABLE `mannschaft` (
  `MannschaftId` int(11) NOT NULL,
  `Name` varchar(64) DEFAULT NULL,
  `Marktwert` double DEFAULT NULL,
  `Gruendungsjahr` int(11) DEFAULT NULL,
  `StadionId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `mannschaft`
--

INSERT INTO `mannschaft` (`MannschaftId`, `Name`, `Marktwert`, `Gruendungsjahr`, `StadionId`) VALUES
(1, 'Dortmund', 508000000, 1909, 1),
(2, 'Real Madrid', 800000000, 1902, 2),
(3, 'Sturm Graz', 42000000, 1909, 3),
(4, 'Manchester City', 4800000000, 1880, 4),
(5, 'AC Milan', 547000000, 1899, 5);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `spieler`
--

CREATE TABLE `spieler` (
  `SpielerId` int(11) NOT NULL,
  `Vorname` varchar(64) DEFAULT NULL,
  `Nachname` varchar(64) DEFAULT NULL,
  `Geburtsdatum` date DEFAULT NULL,
  `Nationalitaet` varchar(16) DEFAULT NULL,
  `Email` varchar(128) DEFAULT NULL,
  `Position` varchar(32) DEFAULT NULL,
  `Marktwert` double DEFAULT NULL,
  `MannschaftId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `spieler`
--

INSERT INTO `spieler` (`SpielerId`, `Vorname`, `Nachname`, `Geburtsdatum`, `Nationalitaet`, `Email`, `Position`, `Marktwert`, `MannschaftId`) VALUES
(21, 'Jude', 'Bellingham', '2003-06-29', 'ENG', 'jude.bellingham@gmail.com', 'Mittelfeld', 120000000, 1),
(22, 'Karim', 'Benzema', '1987-12-19', 'FRA', 'karim.benzema@gmail.com', 'Stuermer', 35000000, 2),
(23, 'Otar', 'Kiteishvili', '1996-06-02', 'GEO', 'otar.kiteishvili@gmail.com', 'Mittelfeld', 760000, 3),
(24, 'Erling', 'Haaland', '2000-07-21', 'NOR', 'erling.haaland@gmail.com', 'Stuermer', 170000000, 4),
(25, 'Zlatan', 'Ibrahimovic', '1981-10-03', 'SWE', 'zlatan.ibrahimovic@gmail.com', 'Stuermer', 2000000, 5);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sponsoren`
--

CREATE TABLE `sponsoren` (
  `SponsorId` int(11) NOT NULL,
  `Name` varchar(64) DEFAULT NULL,
  `Email` varchar(64) DEFAULT NULL,
  `Telefonnummer` int(11) DEFAULT NULL,
  `Chef` varchar(64) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `sponsoren`
--

INSERT INTO `sponsoren` (`SponsorId`, `Name`, `Email`, `Telefonnummer`, `Chef`) VALUES
(1, '1&1', '1&1@gmail.com', 123456789, 'Dommermuth'),
(2, 'Emirates', 'Emirates@gmail.com', 987654321, 'Clark'),
(3, 'Puntigamer', 'Puntigamer@gmail.com', 456789123, 'Mühlberger'),
(4, 'Etihad', 'Etihad@gmail.com', 321654987, 'Panariello');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `sponsor_mannschaft`
--

CREATE TABLE `sponsor_mannschaft` (
  `SponsorMannschaftId` int(11) NOT NULL,
  `SponsorId` int(11) DEFAULT NULL,
  `MannschaftId` int(11) DEFAULT NULL,
  `Sponsorbetrag` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `sponsor_mannschaft`
--

INSERT INTO `sponsor_mannschaft` (`SponsorMannschaftId`, `SponsorId`, `MannschaftId`, `Sponsorbetrag`) VALUES
(1, 1, 1, 22000000),
(2, 2, 2, 28000000),
(3, 3, 3, 7000000),
(4, 4, 4, 61000000),
(5, 2, 2, 19000000);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `stadien`
--

CREATE TABLE `stadien` (
  `StadionID` int(11) NOT NULL,
  `StadtID` int(11) DEFAULT NULL,
  `Zuschaueranzahl` int(11) DEFAULT NULL,
  `Flaeche` int(11) DEFAULT NULL,
  `Baujahr` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `stadien`
--

INSERT INTO `stadien` (`StadionID`, `StadtID`, `Zuschaueranzahl`, `Flaeche`, `Baujahr`) VALUES
(1, 1, 81365, 12570, 1974),
(2, 2, 81044, 12300, 1947),
(3, 3, 15400, 9700, 1997),
(4, 4, 72060, 11600, 1954),
(5, 5, 68450, 12017, 1963);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `stadt`
--

CREATE TABLE `stadt` (
  `StadtID` int(11) NOT NULL,
  `Name` varchar(32) DEFAULT NULL,
  `Land` varchar(8) DEFAULT NULL,
  `Einwohneranzahl` int(11) DEFAULT NULL,
  `Flaeche` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `stadt`
--

INSERT INTO `stadt` (`StadtID`, `Name`, `Land`, `Einwohneranzahl`, `Flaeche`) VALUES
(1, 'Dortmund', 'GER', 587010, 280),
(2, 'Madrid', 'SPA', 3223000, 604),
(3, 'Graz', 'AUT', 283869, 127),
(4, 'Manchester', 'ENG', 553230, 115),
(5, 'Mailand', 'ITA', 1352000, 181);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `trainer`
--

CREATE TABLE `trainer` (
  `TrainerId` int(11) NOT NULL,
  `Vorname` varchar(64) DEFAULT NULL,
  `Nachname` varchar(64) DEFAULT NULL,
  `Geburtsdatum` date DEFAULT NULL,
  `Email` varchar(128) DEFAULT NULL,
  `Nationalitaet` varchar(16) DEFAULT NULL,
  `MannschaftId` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `trainer`
--

INSERT INTO `trainer` (`TrainerId`, `Vorname`, `Nachname`, `Geburtsdatum`, `Email`, `Nationalitaet`, `MannschaftId`) VALUES
(1, 'Edin', 'Terzic', '1982-10-30', 'edin.terzic@example.com', 'GER', 1),
(2, 'Carlo', 'Ancelotti', '1959-06-10', 'carlo.ancelotti@example.com', 'ITA', 2),
(3, 'Christian', 'Ilzer', '1977-10-21', 'christian.ilzer@example.com', 'AUT', 3),
(4, 'Josep', 'Guardiola', '1971-01-18', 'josep.guardiola@example.com', 'SPA', 4),
(5, 'Stefano', 'Pioli', '1965-10-20', 'stefano.pioli@example.com', 'ITA', 5);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `mannschaft`
--
ALTER TABLE `mannschaft`
  ADD PRIMARY KEY (`MannschaftId`),
  ADD KEY `StadionId` (`StadionId`);

--
-- Indizes für die Tabelle `spieler`
--
ALTER TABLE `spieler`
  ADD PRIMARY KEY (`SpielerId`),
  ADD KEY `MannschaftId` (`MannschaftId`);

--
-- Indizes für die Tabelle `sponsoren`
--
ALTER TABLE `sponsoren`
  ADD PRIMARY KEY (`SponsorId`);

--
-- Indizes für die Tabelle `sponsor_mannschaft`
--
ALTER TABLE `sponsor_mannschaft`
  ADD PRIMARY KEY (`SponsorMannschaftId`),
  ADD KEY `SponsorId` (`SponsorId`),
  ADD KEY `MannschaftId` (`MannschaftId`);

--
-- Indizes für die Tabelle `stadien`
--
ALTER TABLE `stadien`
  ADD PRIMARY KEY (`StadionID`),
  ADD KEY `StadtID` (`StadtID`);

--
-- Indizes für die Tabelle `stadt`
--
ALTER TABLE `stadt`
  ADD PRIMARY KEY (`StadtID`);

--
-- Indizes für die Tabelle `trainer`
--
ALTER TABLE `trainer`
  ADD PRIMARY KEY (`TrainerId`),
  ADD KEY `MannschaftId` (`MannschaftId`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `mannschaft`
--
ALTER TABLE `mannschaft`
  MODIFY `MannschaftId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `spieler`
--
ALTER TABLE `spieler`
  MODIFY `SpielerId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT für Tabelle `sponsoren`
--
ALTER TABLE `sponsoren`
  MODIFY `SponsorId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT für Tabelle `sponsor_mannschaft`
--
ALTER TABLE `sponsor_mannschaft`
  MODIFY `SponsorMannschaftId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `stadien`
--
ALTER TABLE `stadien`
  MODIFY `StadionID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `stadt`
--
ALTER TABLE `stadt`
  MODIFY `StadtID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT für Tabelle `trainer`
--
ALTER TABLE `trainer`
  MODIFY `TrainerId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints der exportierten Tabellen
--

--
-- Constraints der Tabelle `mannschaft`
--
ALTER TABLE `mannschaft`
  ADD CONSTRAINT `mannschaft_ibfk_1` FOREIGN KEY (`StadionId`) REFERENCES `stadien` (`StadionID`);

--
-- Constraints der Tabelle `spieler`
--
ALTER TABLE `spieler`
  ADD CONSTRAINT `spieler_ibfk_1` FOREIGN KEY (`MannschaftId`) REFERENCES `mannschaft` (`MannschaftId`);

--
-- Constraints der Tabelle `sponsor_mannschaft`
--
ALTER TABLE `sponsor_mannschaft`
  ADD CONSTRAINT `sponsor_mannschaft_ibfk_1` FOREIGN KEY (`SponsorId`) REFERENCES `sponsoren` (`SponsorId`),
  ADD CONSTRAINT `sponsor_mannschaft_ibfk_2` FOREIGN KEY (`MannschaftId`) REFERENCES `mannschaft` (`MannschaftId`);

--
-- Constraints der Tabelle `stadien`
--
ALTER TABLE `stadien`
  ADD CONSTRAINT `stadien_ibfk_1` FOREIGN KEY (`StadtID`) REFERENCES `stadt` (`StadtID`);

--
-- Constraints der Tabelle `trainer`
--
ALTER TABLE `trainer`
  ADD CONSTRAINT `trainer_ibfk_1` FOREIGN KEY (`MannschaftId`) REFERENCES `mannschaft` (`MannschaftId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
