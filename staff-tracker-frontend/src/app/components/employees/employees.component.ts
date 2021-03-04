import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/models/employee';
import { EmployeeService } from 'src/app/services/employee.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.css']
})
export class EmployeesComponent implements OnInit {

  employees: Employee[];
  showAddEmployeeForm: boolean = false;
  showEditEmployeeForm: boolean = false;
  newEmployee: Employee = new Employee();
  employeeToEdit: Employee;

  constructor(private empService: EmployeeService, private router: Router) { }

  ngOnInit(): void {
    this.empService.getAllEmployees().subscribe(list => {
      this.employees = list
    });
  }

  addEmployee(): void {
    console.log(this.newEmployee);
    this.showAddEmployeeForm = false;
    this.empService.addEmployee(this.newEmployee).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.newEmployee = new Employee();
        this.reload();
      },
      (didntWork) => {
        console.error('Employee Component addEmployee() DID NOT WORK');
        this.reload();
      }
    );
  }

  showEdit(employee: Employee){
    this.showEditEmployeeForm = true;
    this.employeeToEdit = employee;
  }

  editEmployee(): void {
    this.showEditEmployeeForm = false;
    this.empService.editEmployee(this.employeeToEdit, this.employeeToEdit.id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.reload();
      },
      (didntWork) => {
        console.error('Employee Component editEmployee(employee) DID NOT WORK');
        this.reload();
      }
    );

  }

  deleteEmployee(id: number): void {
    this.empService.deleteEmployee(id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.reload();
      },
      (didntWork) => {
        console.error('Employee Component deleteEmployee(id) DID NOT WORK');
        this.reload();
      }
    );

  }

  reload(): void {
    this.empService.getAllEmployees().subscribe(list => {
      this.employees = list
    });
  }

}
