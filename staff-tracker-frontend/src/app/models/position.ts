import { Employee } from "./employee";

export class Position {
    id: number;
    title: string;
    employeeId: number;
    startTime: number;
    endTime: number;
    employee: Employee;

    constructor(
        id?: number,
        title?: string,
        employeeId?: number,
        startTime?: number,
        endTime?: number,
        employee?: Employee

    ) {
        this.id = id;
        this.title = title;
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
    }
}
