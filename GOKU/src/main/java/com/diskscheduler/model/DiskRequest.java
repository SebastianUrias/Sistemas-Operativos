package com.diskscheduler.model;

public class DiskRequest {
    private int platter;   
    private int surface;   
    private int cylinder;  
    private int sector;    
    private boolean attended;

    public DiskRequest(int platter, int surface, int cylinder, int sector) {
        this.platter = platter;
        this.surface = surface;
        this.cylinder = cylinder;
        this.sector = sector;
        this.attended = false;
    }

    // Getters y setters
    public int getPlatter() { return platter; }
    public int getSurface() { return surface; }
    public int getCylinder() { return cylinder; }
    public int getSector() { return sector; }
    public boolean isAttended() { return attended; }
    public void setAttended(boolean attended) { this.attended = attended; }

    @Override
    public String toString() {
        return String.format("Plato: %d, Cara: %d, Cilindro: %d, Sector: %d", 
            platter, surface, cylinder, sector);
    }
}