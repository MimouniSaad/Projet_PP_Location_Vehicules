-- 1. UTILISATEURS
INSERT INTO utilisateur (id, firstname, lastname, email, password, phone, role, address, actif)
VALUES
    (3, 'Client', 'Test',  'client@autoloc.com',  '$2a$10$qCPnYu1YgqT.mhXBKa9MlOnBdrJguXtGXXHsj65y7bIA49/WjPl/6', '0622222222', 'CLIENT',     'Marseille', 1),
    (4, 'Client', 'Two',   'client2@autoloc.com', '$2a$10$qCPnYu1YgqT.mhXBKa9MlOnBdrJguXtGXXHsj65y7bIA49/WjPl/6', '0622222223', 'CLIENT',     'Nice',      1),
    (5, 'Tech',   'One',   'tech@autoloc.com',    '$2a$10$qCPnYu1YgqT.mhXBKa9MlOnBdrJguXtGXXHsj65y7bIA49/WjPl/6', '0633333333', 'Technicien', 'Lille',     1);

-- 2. PERMIS
INSERT INTO permis_conduire (numero, date_obtention, date_expiration, categorie, pays_emission)
VALUES
    ('P123456', '2020-01-10', '2030-01-10', 'B', 'MAROC'),
    ('P654321', '2019-06-15', '2029-06-15', 'C', 'FRANCE');

-- 3. TECHNICIEN
INSERT INTO technicien (id, disponible, specialite) VALUES (5, 1, 'MECANIQUE');

-- 4. CLIENTS
INSERT INTO client (id, permis_numero, statut_reservation)
VALUES (3, 'P123456', null), (4, 'P654321', null);

-- 5. VÉHICULES
INSERT INTO vehicule (id, annee, caution, prix_par_jour, immatriculation, type_boite_vitesse, type_carburant, type, marque, modele, image, statut)
VALUES
    (1, 2022, 500, 60, 'AA-123-BB', 'AUTO',   'DIESEL',  'VOITURE', 'Renault', 'Clio', NULL, 'DISPONIBLE'),
    (2, 2021, 800, 90, 'CC-456-DD', 'MANUEL', 'ESSENCE', 'VOITURE', 'Peugeot', '208',  NULL, 'DISPONIBLE');

-- 6. VOITURES
INSERT INTO voiture (id, nb_places, nb_portes, categorie)
VALUES (1, 5, 5, 'URBAINE'), (2, 5, 3, 'SPORT');

-- 7. RÉSERVATIONS
INSERT INTO reservation (id, date_creation, date_debut, date_fin, date_retour, montant, statut_reservation, client_id, vehicule_id)
VALUES
    (1, '2026-05-01', '2026-05-10', '2026-05-15', NULL, 300, 'CONFIRMEE',  3, 1),
    (2, '2026-05-02', '2026-05-12', '2026-05-18', NULL, 420, 'EN_ATTENTE', 4, 2);

-- 8. PAIEMENT
INSERT INTO paiement (date_paiement, montant, mode_paiement, statut_paiement, reservation_id)
VALUES ('2026-05-01', 300, 'CB', 'CONFIRME', 1);