package com.diskscheduler.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiskScheduler {
    private List<DiskRequest> requests;
    private int currentCylinder;
    private int direction; //1 para adentro, -1 para afuera
    
    public DiskScheduler() {
        requests = new ArrayList<>();
        currentCylinder = 0;
        direction = 1;
    }

    public void generateRequests() {
        Random rand = new Random();
        requests.clear();
        
        //Generar 10 peticiones aleatorias
        for (int i = 0; i < 10; i++) {
            int platter = rand.nextInt(6);    // 0-5
            int surface = rand.nextInt(10);   // 0-9
            int cylinder = rand.nextInt(40);  // 0-39
            int sector = rand.nextInt(16);    // 0-15
            
            requests.add(new DiskRequest(platter, surface, cylinder, sector));
        }
    }

    //Metodos
    public List<DiskRequest> processFCFS() {
        List<DiskRequest> processedRequests = new ArrayList<>(requests);
        // Implementar  
        return processedRequests;
    }

    public List<DiskRequest> processSSTF() {
        List<DiskRequest> processedRequests = new ArrayList<>();
        List<DiskRequest> remainingRequests = new ArrayList<>(requests);
        
        while (!remainingRequests.isEmpty()) {
            DiskRequest closest = findClosestRequest(remainingRequests);
            processedRequests.add(closest);
            remainingRequests.remove(closest);
            currentCylinder = closest.getCylinder();
        }
        
        return processedRequests;
    }

    public List<DiskRequest> processSCAN() {
        List<DiskRequest> processedRequests = new ArrayList<>();
        // Implementar 
        return processedRequests;
    }

    public List<DiskRequest> processSCANdeNPasos(int n) {
        List<DiskRequest> processedRequests = new ArrayList<>();
        // Implementar 
        return processedRequests;
    }

    public List<DiskRequest> processCSCAN() {
        List<DiskRequest> processedRequests = new ArrayList<>();
        // Implementar 
        return processedRequests;
    }

    public List<DiskRequest> processEschenbach() {
        List<DiskRequest> processedRequests = new ArrayList<>();
        // Implementar 
        return processedRequests;
    }

    private DiskRequest findClosestRequest(List<DiskRequest> requests) {
        DiskRequest closest = null;
        int minDistance = Integer.MAX_VALUE;
        
        for (DiskRequest request : requests) {
            int distance = Math.abs(currentCylinder - request.getCylinder());
            if (distance < minDistance) {
                minDistance = distance;
                closest = request;
            }
        }
        
        return closest;
    }
}