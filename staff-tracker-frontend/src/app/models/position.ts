import { Day } from "./day";
import { Employee } from "./employee";

export class Position {
    id: number;
    title: string;
    employeeId: number;
    startTime: string;
    endTime: string;
    shift: string;
    employee: Employee;
    days: Day[];

    constructor(
        id?: number,
        title?: string,
        employeeId?: number,
        startTime?: string,
        endTime?: string,
        shift?: string,
        employee?: Employee,
        days?: Day[]

    ) {
        this.id = id;
        this.title = title;
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.shift = shift;
        this.employee = employee;
        this.days = days;
    }
}
