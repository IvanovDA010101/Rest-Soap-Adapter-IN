package ru.pish.inadapter.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pish.inadapter.models.dto.EmployeeDTO;
import ru.pish.inadapter.models.dto.EmployeesDTO;


@RestController
@RequestMapping("/api/employees")
@Tag(name = "Контроллер для работы с сотрудниками", description = "Добавление, получение и удаление сотрудников из БД")
@Slf4j(topic = "EmployeeController")
public class EmployeeController {

    @Autowired
    private ProducerTemplate producerTemplate;

    @PostMapping("/add")
    @Operation(summary = "Добавление нового сотрудника в БД")
    public ResponseEntity<HttpStatus> addEmployee(@Valid @RequestBody EmployeesDTO employeesDTO) {

            if (employeesDTO == null || employeesDTO.getEmployees() == null){
                log.error("Error while adding employee");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR);

            }
            log.info("new request: {}", employeesDTO);
            producerTemplate.sendBody("direct:SendRestRequestToRabbit", employeesDTO);

            return ResponseEntity.ok(HttpStatus.OK);


    }

    @Hidden
    @GetMapping("/get/{employeeId}")
    @Operation(description = "Получение сотрудника из БД по ид")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        producerTemplate.sendBody("direct:SendRestRequestToRabbit", employeeId);

        return ResponseEntity.ok(EmployeeDTO.builder().build());
    }

    @DeleteMapping("/delete/{employeeId}")
    @Operation(description = "Удаление сотрудника из БД по ид")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(name = "employeeId") Long employeeId) {
        try {
            log.info("new request: {}", employeeId);
            producerTemplate.sendBody("direct:SendRestRequestToRabbit", employeeId);

            return ResponseEntity.ok(HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Error while deleting employee: {}", ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}