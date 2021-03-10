import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Day } from 'src/app/models/day';
import { Week } from 'src/app/models/week';
import { DayService } from 'src/app/services/day.service';
import { PositionService } from 'src/app/services/position.service';
import { WeekService } from 'src/app/services/week.service';
import { Position } from 'src/app/models/position';
import { Employee } from 'src/app/models/employee';
import { EmployeeService } from 'src/app/services/employee.service';

@Component({
  selector: 'app-weeks',
  templateUrl: './weeks.component.html',
  styleUrls: ['./weeks.component.css']
})
export class WeeksComponent implements OnInit {

  // Weeks
  weeks: Week[];
  showAddWeekForm: boolean = false;
  showEditWeekForm: boolean = false;
  showAddPositionForm: boolean = false;
  showEditPositionForm: boolean = false;
  newWeek: Week = new Week();
  weekToEdit: Week;
  viewWeekDiv: boolean = true;
  days: Day[];
  dayIdToAdd: number;
  newPosition: Position = new Position();
  positionToEdit: Position = new Position();
  weekInView: Week = new Week();
  employees: Employee[];
  viewWeekComponent: boolean = true;
  viewEmployeesComponent: boolean = false;
  viewScheduleComponent: boolean = false;

  // Employees
  showAddEmployeeForm: boolean = false;
  showEditEmployeeForm: boolean = false;
  newEmployee: Employee = new Employee();
  employeeToEdit: Employee;
  employeeInView: Employee = new Employee();
  viewEmployees: boolean = true;

  constructor(private weekService: WeekService, private dayService: DayService, private positionService: PositionService, private employeeService: EmployeeService, private router: Router) { }

  ngOnInit(): void {
    this.weekService.getWeeks().subscribe(list => {
      this.weeks = list
    });
    this.employeeService.getAllEmployees().subscribe(list => {
      this.employees = list
    });
  }

  // Weeks
  addWeek(): void {
    console.log(this.newWeek);
    this.showAddWeekForm = false;
    this.weekService.addWeek(this.newWeek).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.newWeek = new Week();
        this.reload();
      },
      (didntWork) => {
        console.error('Week Component addWeek() DID NOT WORK');
        this.reload();
      }
    );
  }

  showEdit(Week: Week){
    this.showEditWeekForm = true;
    this.weekToEdit = Week;
  }

  editWeek(): void {
    this.showEditWeekForm = false;
    this.weekService.editWeek(this.weekToEdit, this.weekToEdit.id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.reload();
      },
      (didntWork) => {
        console.error('Week Component editWeek(Week) DID NOT WORK');
        this.reload();
      }
    );

  }

  deleteWeek(id: number): void {
    this.weekService.deleteWeek(id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.reload();
      },
      (didntWork) => {
        console.error('Week Component deleteWeek(id) DID NOT WORK');
        this.reload();
      }
    );

  }

  reload(): void {
    this.weekService.getWeeks().subscribe(list => {
      this.weeks = list
    });
  }

  reloadWeek(week: Week): void{
    this.dayService.getDaysByRange(week.startDate, week.endDate).subscribe(list => {
      this.days = list
    });
  }

  viewWeek(week: Week): void {
    this.viewWeekDiv = false;
    this.weekInView = week;
    this.dayService.getDaysByRange(week.startDate, week.endDate).subscribe(list => {
      this.days = list
    });
    console.log(this.days);
  }

  addPositionWithDay(): void {
    this.positionService.addPositionWithDay(this.newPosition, this.dayIdToAdd).subscribe(list => {
      this.reloadWeek(this.weekInView);
    });
    this.newPosition = new Position();
  }

  editPosition(): void {
    this.showEditPositionForm = !this.showEditPositionForm;
    this.positionService.editPosition(this.positionToEdit, this.positionToEdit.id).subscribe(list => {
      this.reloadWeek(this.weekInView);
    });
  }

  displayAddPosition(dayId: number): void{
    this.dayIdToAdd = dayId;
    this.showAddPositionForm = !this.showAddPositionForm;
  }

  displayEditPosition(position: Position): void{
    this.positionToEdit = position;
    this.showEditPositionForm = !this.showEditPositionForm;
  }

  deletePosition(positionId: number): void{
    this.positionService.deletePosition(positionId).subscribe(list => {
      this.reloadWeek(this.weekInView);
    })
  }

  // Employees
  addEmployee(): void {
    this.newEmployee.enabled = true;
    console.log(this.newEmployee);
    this.showAddEmployeeForm = false;
    this.employeeService.addEmployee(this.newEmployee).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.newEmployee = new Employee();
        this.empReload();
      },
      (didntWork) => {
        console.error('Employee Component addEmployee() DID NOT WORK');
        this.empReload();
      }
    );
  }

  showEmpEdit(employee: Employee){
    this.showEditEmployeeForm = true;
    this.employeeToEdit = employee;
  }

  editEmployee(): void {
    this.showEditEmployeeForm = false;
    this.employeeService.editEmployee(this.employeeToEdit, this.employeeToEdit.id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.empReload();
      },
      (didntWork) => {
        console.error('Employee Component editEmployee(employee) DID NOT WORK');
        this.empReload();
      }
    );

  }

  deleteEmployee(id: number): void {
    this.employeeService.deleteEmployee(id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.empReload();
      },
      (didntWork) => {
        console.error('Employee Component deleteEmployee(id) DID NOT WORK');
        this.empReload();
      }
    );

  }

  empReload(): void {
    this.employeeService.getAllEmployees().subscribe(list => {
      this.employees = list
    });
  }

  viewEmployeeSchedule(employee: Employee): void {
    this.employeeInView = employee;
    this.viewEmployees = false;
  }

}
