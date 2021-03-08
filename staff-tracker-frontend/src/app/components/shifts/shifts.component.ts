import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Shift } from 'src/app/models/shift';
import { ShiftService } from 'src/app/services/shift.service';

@Component({
  selector: 'app-shifts',
  templateUrl: './shifts.component.html',
  styleUrls: ['./shifts.component.css']
})
export class ShiftsComponent implements OnInit {

  shifts: Shift[];
  showAddShiftForm: boolean = false;
  showEditShiftForm: boolean = false;
  newShift: Shift = new Shift();
  shiftToEdit: Shift;

  constructor(private shiftService: ShiftService, private router: Router) { }

  ngOnInit(): void {
    this.shiftService.getShifts().subscribe(list => {
      this.shifts = list
    });
  }

  addShift(): void {
    console.log(this.newShift);
    this.showAddShiftForm = false;
    this.shiftService.addShift(this.newShift).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.newShift = new Shift();
        this.reload();
      },
      (didntWork) => {
        console.error('Shift Component addShift() DID NOT WORK');
        this.reload();
      }
    );
  }

  showEdit(Shift: Shift){
    this.showEditShiftForm = true;
    this.shiftToEdit = Shift;
  }

  editShift(): void {
    this.showEditShiftForm = false;
    this.shiftService.editShift(this.shiftToEdit, this.shiftToEdit.id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.reload();
      },
      (didntWork) => {
        console.error('Shift Component editShift(Shift) DID NOT WORK');
        this.reload();
      }
    );

  }

  deleteShift(id: number): void {
    this.shiftService.deleteShift(id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.reload();
      },
      (didntWork) => {
        console.error('Shift Component deleteShift(id) DID NOT WORK');
        this.reload();
      }
    );

  }

  reload(): void {
    this.shiftService.getShifts().subscribe(list => {
      this.shifts = list
    });
  }

}
