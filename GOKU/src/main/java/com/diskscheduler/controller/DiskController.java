package com.diskscheduler.controller;

import com.diskscheduler.model.DiskRequest;
import com.diskscheduler.model.DiskScheduler;
import java.util.List;

public class DiskController {
    private DiskScheduler scheduler;

    public DiskController() {
        scheduler = new DiskScheduler();
    }

    public void generateNewRequests() {
        scheduler.generateRequests();
    }

    public List<DiskRequest> processRequests(String algorithm) {
        switch (algorithm) {
            case "FCFS":
                return scheduler.processFCFS();
            case "SSTF":
                return scheduler.processSSTF();
            case "SCAN":
                return scheduler.processSCAN();
            case "SCAN de N Pasos":
                return scheduler.processSCANdeNPasos(4); 
            case "C-SCAN":
                return scheduler.processCSCAN();
            case "Eschenbach":
                return scheduler.processEschenbach();
            default:
                throw new IllegalArgumentException("Algoritmo NO aceptado: " + algorithm);
        }
    }
}