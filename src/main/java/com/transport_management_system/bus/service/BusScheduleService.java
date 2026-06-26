package com.transport_management_system.bus.service;

import java.util.List;

import com.transport_management_system.bus.model.BusSchedule;

public interface BusScheduleService {

    BusSchedule saveBus(BusSchedule bus);

    List<BusSchedule> getAllBuses();

    BusSchedule getBusById(Long id);

    void deleteBus(Long id);

    BusSchedule updateBus(Long id, BusSchedule updatedBus);

    List<BusSchedule> searchByRoute(String route);

    List<BusSchedule> searchByDriverName(String name);

    List<BusSchedule> searchByBusType(String type);

    List<BusSchedule> searchByPickupPoint(String point);

    List<BusSchedule> searchByUserType(String type);

    List<BusSchedule> searchBySchedule(String schedule);
    BusSchedule bookSeat(Long busId, int seatNumber, String passengerName);

    long totalBuses();

    boolean updateDriverInfo(Long id, String name);
}