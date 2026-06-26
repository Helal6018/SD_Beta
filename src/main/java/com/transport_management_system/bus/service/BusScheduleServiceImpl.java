package com.transport_management_system.bus.service;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.transport_management_system.bus.model.BusSchedule;
import com.transport_management_system.bus.model.BusType;
import com.transport_management_system.bus.repository.BusScheduleRepository;

@Service
public class BusScheduleServiceImpl implements BusScheduleService {

    private final BusScheduleRepository repository;

    public BusScheduleServiceImpl(BusScheduleRepository repository) {
        this.repository = repository;
    }

    @Override
    public BusSchedule saveBus(BusSchedule bus) {
        return repository.save(bus);
    }

    @Override
    public List<BusSchedule> getAllBuses() {
        return repository.findAll();
    }

    @Override
    public BusSchedule getBusById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
    }

    @Override
    public void deleteBus(Long id) {
        repository.deleteById(id);
    }

    @Override
    public BusSchedule updateBus(Long id, BusSchedule updatedBus) {
        return repository.findById(id).map(bus -> {
            bus.setRouteName(updatedBus.getRouteName());
            bus.setStartingPoint(updatedBus.getStartingPoint());
            bus.setDestination(updatedBus.getDestination());
            bus.setDepartureTime(updatedBus.getDepartureTime());
            bus.setArrivalTime(updatedBus.getArrivalTime());
            bus.setDriverName(updatedBus.getDriverName());
            bus.setAvailableSeats(updatedBus.getAvailableSeats());
            bus.setBusType(updatedBus.getBusType());
            return repository.save(bus);
        }).orElseThrow(() -> new RuntimeException("Bus not found"));
    }

    @Override
    public List<BusSchedule> searchByRoute(String route) {
        return repository.findByRouteName(route);
    }

    @Override
    public List<BusSchedule> searchByDriverName(String name) {
        return repository.findByDriverName(name);
    }

    @Override
    public List<BusSchedule> searchByBusType(String type) {
        try {
            BusType enumType = BusType.valueOf(type.toUpperCase());
            return repository.findByBusType(enumType);
        } catch (IllegalArgumentException e) {
            return List.of();
        }
    }

    @Override
    public List<BusSchedule> searchByPickupPoint(String point) {
        return repository.findByStartingPoint(point);
    }

    @Override
    public List<BusSchedule> searchByUserType(String type) {
        return searchByBusType(type);
    }

    @Override
    public List<BusSchedule> searchBySchedule(String schedule) {
        try {
            LocalTime time = LocalTime.parse(schedule);
            return repository.findByDepartureTime(time);
        } catch (DateTimeParseException e) {
            return List.of();
        }
    }

    @Override
    public long totalBuses() {
        return repository.count();
    }
    
    @Override
    public boolean updateDriverInfo(Long id, String name) {
        return repository.findById(id).map(bus -> {
            bus.setDriverName(name);
            repository.save(bus);
            return true;
        }).orElse(false);
    }
    @Override
    public BusSchedule bookSeat(Long busId, int seatNumber, String passengerName) {
    BusSchedule bus = repository.findById(busId).orElse(null);

        if (bus == null) return null;

        if (bus.getAvailableSeats() > 0) {
            bus.setAvailableSeats(bus.getAvailableSeats() - 1);
            return repository.save(bus);
        } else {
            return null; 
        }
    }
    
}