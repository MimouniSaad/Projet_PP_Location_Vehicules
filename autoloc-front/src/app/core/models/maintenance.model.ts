export interface OrdreMaintenance {
  id: number;
  vehiculeId?: number;
  vehiculeMarque?: string;
  vehiculeModele?: string;
  vehiculeImmatriculation?: string;
  technicienId?: number;
  technicienNom?: string;
  technicienPrenom?: string;
  typeReparation: string;
  description?: string;
  statut: 'SIGNALE' | 'ASSIGNE' | 'EN_COURS' | 'RESOLU' | 'ABANDONNE';
  dateSignal?: string;
  dateResolution?: string;
  coutReparation?: number;
}

export interface MaintenanceRequest {
  vehiculeImmatriculation: string;
  typeReparation: string;
  description?: string;
  technicienId?: number | null;
}
