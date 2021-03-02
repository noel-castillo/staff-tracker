import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/models/employee';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  employees : Employee[];

  constructor(private empService : EmployeeService) { }

  ngOnInit(): void {
    this.empService.getAllEmployees().subscribe(list => {
      this.employees = list
    });
  }

}
