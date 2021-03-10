import { Component, OnInit } from '@angular/core';
import { Employee } from 'src/app/models/employee';
import { Position } from 'src/app/models/position';
import { EmployeeService } from 'src/app/services/employee.service';
import { PositionService } from 'src/app/services/position.service';

@Component({
  selector: 'app-positions',
  templateUrl: './positions.component.html',
  styleUrls: ['./positions.component.css']
})
export class PositionsComponent implements OnInit {

  positions: Position[];
  newPosition: Position = new Position();
  positionToEdit: Position;
  showAddPositionForm: boolean = false;
  showEditPositionForm: boolean = false;

  constructor(private posService: PositionService, private empService: EmployeeService) { }

  ngOnInit(): void {
    this.posService.getAllPositions().subscribe(list => {
      this.positions = list;
    });

  }

  addPosition(): void {
    console.log(this.newPosition);
    this.showAddPositionForm = false;
    this.posService.addPosition(this.newPosition).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.newPosition = new Position();
        this.reload();
      },
      (didntWork) => {
        console.error('Position Component addPosition() DID NOT WORK');
        this.reload();
      }
    );
  }

  showEdit(position: Position) {
    this.showEditPositionForm = true;
    this.positionToEdit = position;
  }

  editPosition(): void {
    this.showEditPositionForm = false;
    this.posService.editPosition(this.positionToEdit, this.positionToEdit.id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.reload();
      },
      (didntWork) => {
        console.error('Position Component editPosition(Position) DID NOT WORK');
        this.reload();
      }
    );

    this.reload();

  }

  deletePosition(id: number): void {
    this.posService.deletePosition(id).subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.reload();
      },
      (didntWork) => {
        console.error('Position Component deletePosition(id) DID NOT WORK');
        this.reload();
      }
    );

  }

  reload(): void {
    this.posService.getAllPositions().subscribe(list => {
      this.positions = list
    });
  }

}
