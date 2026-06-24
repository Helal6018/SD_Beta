package com.transport_management_system.bus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.transport_management_system.bus.model.BusSchedule;
import com.transport_management_system.bus.service.BusScheduleService;

@RestController
@RequestMapping("/buses")
public class BusScheduleController {

    private final BusScheduleService service;

    // Constructor
    public BusScheduleController(BusScheduleService service) {
        this.service = service;
    }

    @GetMapping
    public List<BusSchedule> getAllBuses() {
        return service.getAllBuses();
    }

    @GetMapping("/{id}")
    public BusSchedule getBusById(@PathVariable Long id) {
        return service.getBusById(id);
    }

    @PostMapping
    public BusSchedule addBus(@RequestBody BusSchedule bus) {
        return service.saveBus(bus);
    }

    @PutMapping("/{id}")
    public BusSchedule updateBus(@PathVariable Long id, @RequestBody BusSchedule updatedBus) {
        return service.updateBus(id, updatedBus);
    }

    @DeleteMapping("/{id}")
    public void deleteBus(@PathVariable Long id) {
        service.deleteBus(id);
    }

    @GetMapping("/route/{route}")
    public List<BusSchedule> searchByRoute(@PathVariable String route) {
        return service.searchByRoute(route);
    }

    @GetMapping("/driver/{name}")
    public List<BusSchedule> searchByDriverName(@PathVariable String name) {
        return service.searchByDriverName(name);
    }

    @GetMapping("/type/{type}")
    public List<BusSchedule> searchByBusType(@PathVariable String type) {
        return service.searchByBusType(type);
    }

    @GetMapping("/pickup/{point}")
    public List<BusSchedule> searchByPickupPoint(@PathVariable String point) {
        return service.searchByPickupPoint(point);
    }

    @GetMapping("/usertype/{type}")
    public List<BusSchedule> searchByUserType(@PathVariable String type) {
        return service.searchByUserType(type);
    }

    @GetMapping("/schedule/{schedule}")
    public List<BusSchedule> searchBySchedule(@PathVariable String schedule) {
        return service.searchBySchedule(schedule);
    }

    @GetMapping("/count")
    public long totalBuses() {
        return service.totalBuses();
    }

    @PatchMapping("/{id}/driver")
    public boolean updateDriverInfo(@PathVariable Long id,
                                    @RequestParam String name,
                                    @RequestParam(required = false) String phone) {
        return service.updateDriverInfo(id, name);
    }
}
