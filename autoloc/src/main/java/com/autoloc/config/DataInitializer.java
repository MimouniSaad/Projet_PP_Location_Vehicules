package com.autoloc.config;

import com.autoloc.enums.*;
import com.autoloc.model.*;
import com.autoloc.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VehiculeRepository vehiculeRepository;
    private final TechnicienRepository technicienRepository;
    private final ClientRepository clientRepository;
    private final ReservationRepository reservationRepository;
    private final OptionRepository optionRepository;
    private final NotificationRepository notificationRepository;
    private final MaintenanceRepository maintenanceRepository;

    @Override
    public void run(String... args) {

        if (userRepository.existsByEmail("superadmin@autoloc.fr")) {
            System.out.println(">>> Données déjà initialisées, skip.");
            return;
        }

        // ═══════════════════════════════════════════════════
        // SUPER ADMIN
        // ═══════════════════════════════════════════════════
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setFirstname("Super");
        superAdmin.setLastname("Admin");
        superAdmin.setEmail("superadmin@autoloc.fr");
        superAdmin.setPassword(passwordEncoder.encode("Admin@1234"));
        superAdmin.setRole(userRole.SUPER_ADMIN);
        superAdmin.setActif(true);
        superAdmin.setPhone("0600000001");
        superAdmin.setAddress("1 rue du Siège, Paris");
        userRepository.save(superAdmin);

        // ═══════════════════════════════════════════════════
        // ADMINS
        // ═══════════════════════════════════════════════════
        Admin admin1 = new Admin();
        admin1.setFirstname("Ahmed");
        admin1.setLastname("Benali");
        admin1.setEmail("ahmed.benali@autoloc.fr");
        admin1.setPassword(passwordEncoder.encode("Admin@1234"));
        admin1.setRole(userRole.ADMIN);
        admin1.setActif(true);
        admin1.setPhone("0600000002");
        admin1.setAddress("12 avenue des Champs, Lyon");
        userRepository.save(admin1);

        Admin admin2 = new Admin();
        admin2.setFirstname("Sara");
        admin2.setLastname("Morin");
        admin2.setEmail("sara.morin@autoloc.fr");
        admin2.setPassword(passwordEncoder.encode("Admin@1234"));
        admin2.setRole(userRole.ADMIN);
        admin2.setActif(true);
        admin2.setPhone("0600000003");
        admin2.setAddress("5 rue de la Paix, Marseille");
        userRepository.save(admin2);

        // ═══════════════════════════════════════════════════
        // TECHNICIENS
        // ═══════════════════════════════════════════════════
        Technicien tech1 = new Technicien();
        tech1.setFirstname("Karim");
        tech1.setLastname("Mansouri");
        tech1.setEmail("karim.mansouri@autoloc.fr");
        tech1.setPassword(passwordEncoder.encode("Tech@1234"));
        tech1.setRole(userRole.Technicien);
        tech1.setActif(true);
        tech1.setPhone("0611111101");
        tech1.setAddress("8 rue des Mécaniciens, Paris");
        tech1.setSpecialite("Moteur et transmission");
        tech1.setDisponible(true);
        userRepository.save(tech1);

        Technicien tech2 = new Technicien();
        tech2.setFirstname("Youssef");
        tech2.setLastname("Hamidi");
        tech2.setEmail("youssef.hamidi@autoloc.fr");
        tech2.setPassword(passwordEncoder.encode("Tech@1234"));
        tech2.setRole(userRole.Technicien);
        tech2.setActif(true);
        tech2.setPhone("0611111102");
        tech2.setAddress("3 impasse des Ateliers, Toulouse");
        tech2.setSpecialite("Électricité automobile");
        tech2.setDisponible(true);
        userRepository.save(tech2);

        Technicien tech3 = new Technicien();
        tech3.setFirstname("Nicolas");
        tech3.setLastname("Perrin");
        tech3.setEmail("nicolas.perrin@autoloc.fr");
        tech3.setPassword(passwordEncoder.encode("Tech@1234"));
        tech3.setRole(userRole.Technicien);
        tech3.setActif(true);
        tech3.setPhone("0611111103");
        tech3.setAddress("22 boulevard du Garage, Bordeaux");
        tech3.setSpecialite("Carrosserie et peinture");
        tech3.setDisponible(false);
        userRepository.save(tech3);

        // ═══════════════════════════════════════════════════
        // PERMIS DE CONDUIRE
        // ═══════════════════════════════════════════════════
        PermisConduire permis1 = new PermisConduire();
        permis1.setNumero("FR-2024-001");
        permis1.setCategorie(categoriePermis.B);
        permis1.setDateObtention(new Date(2015 - 1900, 5, 15));
        permis1.setDateExpiration(new Date(2030 - 1900, 5, 15));
        permis1.setPaysEmission(paysEmission.FRANCE);

        PermisConduire permis2 = new PermisConduire();
        permis2.setNumero("MA-2023-045");
        permis2.setCategorie(categoriePermis.B);
        permis2.setDateObtention(new Date(2018 - 1900, 3, 20));
        permis2.setDateExpiration(new Date(2028 - 1900, 3, 20));
        permis2.setPaysEmission(paysEmission.MAROC);

        PermisConduire permis3 = new PermisConduire();
        permis3.setNumero("FR-2022-789");
        permis3.setCategorie(categoriePermis.C);
        permis3.setDateObtention(new Date(2010 - 1900, 8, 10));
        permis3.setDateExpiration(new Date(2025 - 1900, 8, 10));
        permis3.setPaysEmission(paysEmission.FRANCE);

        PermisConduire permis4 = new PermisConduire();
        permis4.setNumero("BE-2021-333");
        permis4.setCategorie(categoriePermis.B);
        permis4.setDateObtention(new Date(2019 - 1900, 1, 5));
        permis4.setDateExpiration(new Date(2029 - 1900, 1, 5));
        permis4.setPaysEmission(paysEmission.BELGIQUE);

        PermisConduire permis5 = new PermisConduire();
        permis5.setNumero("DZ-2020-112");
        permis5.setCategorie(categoriePermis.B);
        permis5.setDateObtention(new Date(2016 - 1900, 11, 1));
        permis5.setDateExpiration(new Date(2031 - 1900, 11, 1));
        permis5.setPaysEmission(paysEmission.ALGERIE);

        // ═══════════════════════════════════════════════════
        // CLIENTS
        // ═══════════════════════════════════════════════════
        Client client1 = new Client();
        client1.setFirstname("Marie");
        client1.setLastname("Dupont");
        client1.setEmail("marie.dupont@gmail.com");
        client1.setPassword(passwordEncoder.encode("Client@1234"));
        client1.setRole(userRole.CLIENT);
        client1.setActif(true);
        client1.setPhone("0622222201");
        client1.setAddress("15 rue Victor Hugo, Paris");
        client1.setPermisConduire(permis1);
        client1.setStatutReservation(statutReservation.EN_ATTENTE);
        userRepository.save(client1);

        Client client2 = new Client();
        client2.setFirstname("Omar");
        client2.setLastname("Zaidi");
        client2.setEmail("omar.zaidi@gmail.com");
        client2.setPassword(passwordEncoder.encode("Client@1234"));
        client2.setRole(userRole.CLIENT);
        client2.setActif(true);
        client2.setPhone("0622222202");
        client2.setAddress("7 avenue Mohammed V, Casablanca");
        client2.setPermisConduire(permis2);
        client2.setStatutReservation(statutReservation.CONFIRMEE);
        userRepository.save(client2);

        Client client3 = new Client();
        client3.setFirstname("Julie");
        client3.setLastname("Bernard");
        client3.setEmail("julie.bernard@gmail.com");
        client3.setPassword(passwordEncoder.encode("Client@1234"));
        client3.setRole(userRole.CLIENT);
        client3.setActif(true);
        client3.setPhone("0622222203");
        client3.setAddress("33 rue de la République, Lyon");
        client3.setPermisConduire(permis3);
        client3.setStatutReservation(statutReservation.TERMINEE);
        userRepository.save(client3);

        Client client4 = new Client();
        client4.setFirstname("Thomas");
        client4.setLastname("Leroy");
        client4.setEmail("thomas.leroy@gmail.com");
        client4.setPassword(passwordEncoder.encode("Client@1234"));
        client4.setRole(userRole.CLIENT);
        client4.setActif(true);
        client4.setPhone("0622222204");
        client4.setAddress("2 place Flagey, Bruxelles");
        client4.setPermisConduire(permis4);
        client4.setStatutReservation(statutReservation.ANNULEE);
        userRepository.save(client4);

        Client client5 = new Client();
        client5.setFirstname("Amina");
        client5.setLastname("Cherif");
        client5.setEmail("amina.cherif@gmail.com");
        client5.setPassword(passwordEncoder.encode("Client@1234"));
        client5.setRole(userRole.CLIENT);
        client5.setActif(true);
        client5.setPhone("0622222205");
        client5.setAddress("9 rue Didouche Mourad, Alger");
        client5.setPermisConduire(permis5);
        client5.setStatutReservation(statutReservation.EN_ATTENTE);
        userRepository.save(client5);

        // ═══════════════════════════════════════════════════
        // OPTIONS
        // ═══════════════════════════════════════════════════
        Option gps = new Option(); gps.setNom("GPS");
        Option siegeBebe = new Option(); siegeBebe.setNom("Siège bébé");
        Option clim = new Option(); clim.setNom("Climatisation");
        Option telepage = new Option(); telepage.setNom("Télépéage");
        Option assurancePlus = new Option(); assurancePlus.setNom("Assurance Plus");
        optionRepository.saveAll(List.of(gps, siegeBebe, clim, telepage, assurancePlus));

        // ═══════════════════════════════════════════════════
        // VOITURES
        // ═══════════════════════════════════════════════════
        Voiture v1 = new Voiture();
        v1.setMarque("Renault");
        v1.setModele("Clio");
        v1.setImmatriculation("AB-123-CD");
        v1.setAnnee(2021);
        v1.setPrixParJour(45.0);
        v1.setCaution(300.0);
        v1.setStatut(statutVehicule.DISPONIBLE);
        v1.setTypeCarburant("Essence");
        v1.setTypeBoiteVitesse("Manuelle");
        v1.setNbPortes(5);
        v1.setNbPlaces(5);
        v1.setCategorie("Citadine");
        v1.setOptions(List.of(gps, clim));
        vehiculeRepository.save(v1);

        Voiture v2 = new Voiture();
        v2.setMarque("Peugeot");
        v2.setModele("308");
        v2.setImmatriculation("EF-456-GH");
        v2.setAnnee(2022);
        v2.setPrixParJour(55.0);
        v2.setCaution(400.0);
        v2.setStatut(statutVehicule.LOUE);
        v2.setTypeCarburant("Diesel");
        v2.setTypeBoiteVitesse("Automatique");
        v2.setNbPortes(5);
        v2.setNbPlaces(5);
        v2.setCategorie("Berline");
        v2.setOptions(List.of(gps, clim, telepage));
        vehiculeRepository.save(v2);

        Voiture v3 = new Voiture();
        v3.setMarque("Toyota");
        v3.setModele("Yaris");
        v3.setImmatriculation("IJ-789-KL");
        v3.setAnnee(2020);
        v3.setPrixParJour(40.0);
        v3.setCaution(250.0);
        v3.setStatut(statutVehicule.DISPONIBLE);
        v3.setTypeCarburant("Hybride");
        v3.setTypeBoiteVitesse("Automatique");
        v3.setNbPortes(5);
        v3.setNbPlaces(5);
        v3.setCategorie("Citadine");
        v3.setOptions(List.of(clim));
        vehiculeRepository.save(v3);

        Voiture v4 = new Voiture();
        v4.setMarque("BMW");
        v4.setModele("Serie 3");
        v4.setImmatriculation("MN-321-OP");
        v4.setAnnee(2023);
        v4.setPrixParJour(120.0);
        v4.setCaution(800.0);
        v4.setStatut(statutVehicule.DISPONIBLE);
        v4.setTypeCarburant("Essence");
        v4.setTypeBoiteVitesse("Automatique");
        v4.setNbPortes(4);
        v4.setNbPlaces(5);
        v4.setCategorie("Premium");
        v4.setOptions(List.of(gps, clim, telepage, assurancePlus));
        vehiculeRepository.save(v4);

        Voiture v5 = new Voiture();
        v5.setMarque("Dacia");
        v5.setModele("Sandero");
        v5.setImmatriculation("QR-654-ST");
        v5.setAnnee(2019);
        v5.setPrixParJour(30.0);
        v5.setCaution(200.0);
        v5.setStatut(statutVehicule.EN_MAINTENANCE);
        v5.setTypeCarburant("Essence");
        v5.setTypeBoiteVitesse("Manuelle");
        v5.setNbPortes(5);
        v5.setNbPlaces(5);
        v5.setCategorie("Économique");
        v5.setOptions(List.of());
        vehiculeRepository.save(v5);

        Voiture v6 = new Voiture();
        v6.setMarque("Mercedes");
        v6.setModele("Classe A");
        v6.setImmatriculation("UV-987-WX");
        v6.setAnnee(2022);
        v6.setPrixParJour(100.0);
        v6.setCaution(700.0);
        v6.setStatut(statutVehicule.DISPONIBLE);
        v6.setTypeCarburant("Diesel");
        v6.setTypeBoiteVitesse("Automatique");
        v6.setNbPortes(5);
        v6.setNbPlaces(5);
        v6.setCategorie("Premium");
        v6.setOptions(List.of(gps, clim, assurancePlus));
        vehiculeRepository.save(v6);

        // ═══════════════════════════════════════════════════
        // CAMIONS
        // ═══════════════════════════════════════════════════
        Camion c1 = new Camion();
        c1.setMarque("Mercedes");
        c1.setModele("Actros");
        c1.setImmatriculation("CA-001-MZ");
        c1.setAnnee(2020);
        c1.setPrixParJour(200.0);
        c1.setCaution(1500.0);
        c1.setStatut(statutVehicule.DISPONIBLE);
        c1.setTypeCarburant("Diesel");
        c1.setTypeBoiteVitesse("Manuelle");
        c1.setTonnage(20.0);
        c1.setVolume(80.0);
        c1.setLongueur(12.0);
        c1.setElevator(true);
        vehiculeRepository.save(c1);

        Camion c2 = new Camion();
        c2.setMarque("Renault");
        c2.setModele("Master");
        c2.setImmatriculation("CA-002-MZ");
        c2.setAnnee(2021);
        c2.setPrixParJour(150.0);
        c2.setCaution(1000.0);
        c2.setStatut(statutVehicule.LOUE);
        c2.setTypeCarburant("Diesel");
        c2.setTypeBoiteVitesse("Manuelle");
        c2.setTonnage(5.0);
        c2.setVolume(20.0);
        c2.setLongueur(6.0);
        c2.setElevator(false);
        vehiculeRepository.save(c2);

        Camion c3 = new Camion();
        c3.setMarque("Iveco");
        c3.setModele("Daily");
        c3.setImmatriculation("CA-003-MZ");
        c3.setAnnee(2019);
        c3.setPrixParJour(130.0);
        c3.setCaution(900.0);
        c3.setStatut(statutVehicule.DISPONIBLE);
        c3.setTypeCarburant("Diesel");
        c3.setTypeBoiteVitesse("Automatique");
        c3.setTonnage(3.5);
        c3.setVolume(15.0);
        c3.setLongueur(5.0);
        c3.setElevator(true);
        vehiculeRepository.save(c3);

        // ═══════════════════════════════════════════════════
        // RESERVATIONS
        // ═══════════════════════════════════════════════════
        Reservation r1 = new Reservation();
        r1.setDateCreation(LocalDate.now().minusDays(10));
        r1.setDateDebut(LocalDate.now().minusDays(8));
        r1.setDateFin(LocalDate.now().minusDays(5));
        r1.setDateRetour(LocalDate.now().minusDays(5));
        r1.setMontant(3 * v1.getPrixParJour());
        r1.setStatutReservation(statutReservation.TERMINEE);
        r1.setClient(client1);
        r1.setVehicule(v1);
        reservationRepository.save(r1);

        Reservation r2 = new Reservation();
        r2.setDateCreation(LocalDate.now().minusDays(5));
        r2.setDateDebut(LocalDate.now().minusDays(3));
        r2.setDateFin(LocalDate.now().plusDays(2));
        r2.setMontant(5 * v2.getPrixParJour());
        r2.setStatutReservation(statutReservation.CONFIRMEE);
        r2.setClient(client2);
        r2.setVehicule(v2);
        reservationRepository.save(r2);

        Reservation r3 = new Reservation();
        r3.setDateCreation(LocalDate.now().minusDays(2));
        r3.setDateDebut(LocalDate.now().plusDays(1));
        r3.setDateFin(LocalDate.now().plusDays(4));
        r3.setMontant(3 * v3.getPrixParJour());
        r3.setStatutReservation(statutReservation.EN_ATTENTE);
        r3.setClient(client3);
        r3.setVehicule(v3);
        reservationRepository.save(r3);

        Reservation r4 = new Reservation();
        r4.setDateCreation(LocalDate.now().minusDays(15));
        r4.setDateDebut(LocalDate.now().minusDays(12));
        r4.setDateFin(LocalDate.now().minusDays(10));
        r4.setDateRetour(LocalDate.now().minusDays(10));
        r4.setMontant(2 * v4.getPrixParJour());
        r4.setStatutReservation(statutReservation.ANNULEE);
        r4.setClient(client4);
        r4.setVehicule(v4);
        reservationRepository.save(r4);

        Reservation r5 = new Reservation();
        r5.setDateCreation(LocalDate.now().minusDays(1));
        r5.setDateDebut(LocalDate.now().plusDays(2));
        r5.setDateFin(LocalDate.now().plusDays(6));
        r5.setMontant(4 * v6.getPrixParJour());
        r5.setStatutReservation(statutReservation.EN_ATTENTE);
        r5.setClient(client5);
        r5.setVehicule(v6);
        reservationRepository.save(r5);

        Reservation r6 = new Reservation();
        r6.setDateCreation(LocalDate.now().minusDays(20));
        r6.setDateDebut(LocalDate.now().minusDays(18));
        r6.setDateFin(LocalDate.now().minusDays(13));
        r6.setDateRetour(LocalDate.now().minusDays(13));
        r6.setMontant(5 * c1.getPrixParJour());
        r6.setStatutReservation(statutReservation.TERMINEE);
        r6.setClient(client1);
        r6.setVehicule(c1);
        reservationRepository.save(r6);

        // ═══════════════════════════════════════════════════
        // PAIEMENTS
        // ═══════════════════════════════════════════════════
        Paiement p1 = new Paiement();
        p1.setMontant(r1.getMontant());
        p1.setDatePaiement(LocalDate.now().minusDays(8));
        p1.setModePaiement(modePaiement.CB);
        p1.setStatutPaiement(statutPaiement.CONFIRME);
        p1.setReservation(r1);

        Paiement p2 = new Paiement();
        p2.setMontant(r2.getMontant());
        p2.setDatePaiement(LocalDate.now().minusDays(3));
        p2.setModePaiement(modePaiement.ESPECE);
        p2.setStatutPaiement(statutPaiement.CONFIRME);
        p2.setReservation(r2);

        Paiement p3 = new Paiement();
        p3.setMontant(r3.getMontant());
        p3.setDatePaiement(LocalDate.now().minusDays(2));
        p3.setModePaiement(modePaiement.CB);
        p3.setStatutPaiement(statutPaiement.EN_ATTENTE);
        p3.setReservation(r3);

        Paiement p4 = new Paiement();
        p4.setMontant(r4.getMontant());
        p4.setDatePaiement(LocalDate.now().minusDays(12));
        p4.setModePaiement(modePaiement.CHEQUE);
        p4.setStatutPaiement(statutPaiement.REMBOURSE);
        p4.setReservation(r4);

        Paiement p5 = new Paiement();
        p5.setMontant(r5.getMontant());
        p5.setDatePaiement(LocalDate.now().minusDays(1));
        p5.setModePaiement(modePaiement.CB);
        p5.setStatutPaiement(statutPaiement.EN_ATTENTE);
        p5.setReservation(r5);

        Paiement p6 = new Paiement();
        p6.setMontant(r6.getMontant());
        p6.setDatePaiement(LocalDate.now().minusDays(18));
        p6.setModePaiement(modePaiement.ESPECE);
        p6.setStatutPaiement(statutPaiement.CONFIRME);
        p6.setReservation(r6);

        // Sauvegarder via les reservations (cascade)
        r1.setPaiement(p1); reservationRepository.save(r1);
        r2.setPaiement(p2); reservationRepository.save(r2);
        r3.setPaiement(p3); reservationRepository.save(r3);
        r4.setPaiement(p4); reservationRepository.save(r4);
        r5.setPaiement(p5); reservationRepository.save(r5);
        r6.setPaiement(p6); reservationRepository.save(r6);

        // ═══════════════════════════════════════════════════
        // ORDRES DE MAINTENANCE
        // ═══════════════════════════════════════════════════
        OrdreMaintenance om1 = new OrdreMaintenance();
        om1.setImmatriculation(v5.getImmatriculation());
        om1.setTypeReparation("Vidange et filtres");
        om1.setDescription("Vidange moteur + remplacement filtre à huile et à air");
        om1.setDateSignal(LocalDate.now().minusDays(7));
        om1.setStatut(statutMaintenance.EN_COURS);
        om1.setVehicule(v5);
        om1.setTechnicien(tech3);
        om1.setAdmin(admin1);
        maintenanceRepository.save(om1);

        OrdreMaintenance om2 = new OrdreMaintenance();
        om2.setImmatriculation(v1.getImmatriculation());
        om2.setTypeReparation("Remplacement plaquettes de frein");
        om2.setDescription("Usure des plaquettes avant signalée par le client");
        om2.setDateSignal(LocalDate.now().minusDays(30));
        om2.setDateResolution(LocalDate.now().minusDays(28));
        om2.setCoutReparation(250.0);
        om2.setStatut(statutMaintenance.RESOLU);
        om2.setVehicule(v1);
        om2.setTechnicien(tech1);
        om2.setAdmin(admin1);
        maintenanceRepository.save(om2);

        OrdreMaintenance om3 = new OrdreMaintenance();
        om3.setImmatriculation(c2.getImmatriculation());
        om3.setTypeReparation("Panne électrique");
        om3.setDescription("Défaut alternateur détecté lors du diagnostic");
        om3.setDateSignal(LocalDate.now().minusDays(3));
        om3.setStatut(statutMaintenance.SIGNALE);
        om3.setVehicule(c2);
        om3.setTechnicien(tech2);
        om3.setAdmin(admin2);
        maintenanceRepository.save(om3);

        OrdreMaintenance om4 = new OrdreMaintenance();
        om4.setImmatriculation(v3.getImmatriculation());
        om4.setTypeReparation("Révision générale");
        om4.setDescription("Révision complète 60 000 km");
        om4.setDateSignal(LocalDate.now().minusDays(60));
        om4.setDateResolution(LocalDate.now().minusDays(57));
        om4.setCoutReparation(450.0);
        om4.setStatut(statutMaintenance.RESOLU);
        om4.setVehicule(v3);
        om4.setTechnicien(tech1);
        om4.setAdmin(admin2);
        maintenanceRepository.save(om4);

        // ═══════════════════════════════════════════════════
        // NOTIFICATIONS
        // ═══════════════════════════════════════════════════
        Notification n1 = new Notification();
        n1.setTitre("Réservation confirmée");
        n1.setMessage("Votre réservation pour la Peugeot 308 a été confirmée.");
        n1.setDateEnvoi(LocalDateTime.now().minusDays(3));
        n1.setUtilisateur(client2);
        notificationRepository.save(n1);

        Notification n2 = new Notification();
        n2.setTitre("Paiement reçu");
        n2.setMessage("Votre paiement de " + r1.getMontant() + "€ a bien été reçu.");
        n2.setDateEnvoi(LocalDateTime.now().minusDays(8));
        n2.setUtilisateur(client1);
        notificationRepository.save(n2);

        Notification n3 = new Notification();
        n3.setTitre("Réservation annulée");
        n3.setMessage("Votre réservation a été annulée. Un remboursement sera effectué.");
        n3.setDateEnvoi(LocalDateTime.now().minusDays(12));
        n3.setUtilisateur(client4);
        notificationRepository.save(n3);

        Notification n4 = new Notification();
        n4.setTitre("Nouvelle réservation");
        n4.setMessage("Nouvelle demande de réservation reçue de Marie Dupont.");
        n4.setDateEnvoi(LocalDateTime.now().minusHours(5));
        n4.setUtilisateur(admin1);
        notificationRepository.save(n4);

        Notification n5 = new Notification();
        n5.setTitre("Ordre de maintenance assigné");
        n5.setMessage("Un nouvel ordre de maintenance vous a été assigné pour le véhicule " + v5.getImmatriculation());
        n5.setDateEnvoi(LocalDateTime.now().minusDays(7));
        n5.setUtilisateur(tech3);
        notificationRepository.save(n5);

        Notification n6 = new Notification();
        n6.setTitre("Bienvenue chez AutoLoc");
        n6.setMessage("Votre compte a été créé avec succès. Bonne location !");
        n6.setDateEnvoi(LocalDateTime.now().minusDays(20));
        n6.setUtilisateur(client5);
        notificationRepository.save(n6);

        System.out.println("════════════════════════════════════════════");
        System.out.println(">>> DataInitializer terminé avec succès !");
        System.out.println(">>> 1 SuperAdmin  | superadmin@autoloc.fr / Admin@1234");
        System.out.println(">>> 2 Admins      | ahmed.benali@autoloc.fr / Admin@1234");
        System.out.println(">>> 3 Techniciens | karim.mansouri@autoloc.fr / Tech@1234");
        System.out.println(">>> 5 Clients     | marie.dupont@gmail.com / Client@1234");
        System.out.println(">>> 6 Voitures + 3 Camions");
        System.out.println(">>> 6 Réservations + 6 Paiements");
        System.out.println(">>> 4 Ordres de maintenance + 6 Notifications");
        System.out.println("════════════════════════════════════════════");
    }
}