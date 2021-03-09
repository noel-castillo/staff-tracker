import { Employee } from "./employee";

export class Position {
    id: number;
    title: string;
    employeeId: number;
    startTime: number;
    endTime: number;
    shift: string;
    employee: Employee;

    constructor(
        id?: number,
        title?: string,
        employeeId?: number,
        startTime?: number,
        endTime?: number,
        shift?: string,
        employee?: Employee

    ) {
        this.id = id;
        this.title = title;
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shift = shift;
        this.employee = employee;
    }
}
