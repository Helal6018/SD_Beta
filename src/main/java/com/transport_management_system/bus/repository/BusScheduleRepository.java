package com.transport_management_system.bus.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.transport_management_system.bus.model.BusSchedule;
import com.transport_management_system.bus.model.BusType;

public interface BusScheduleRepository extends JpaRepository<BusSchedule, Long> {
    List<BusSchedule> findByRouteName(String routeName);
    List<BusSchedule> findByDriverName(String driverName);
    List<BusSchedule> findByBusType(BusType busType);
    List<BusSchedule> findByStartingPoint(String startingPoint);
    List<BusSchedule> findByDepartureTime(LocalTime departureTime);
}