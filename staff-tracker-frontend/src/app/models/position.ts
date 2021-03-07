export class Position {
    id: number;
    title: string;
    employeeId: number;
    startTime: number;
    endTime: number;

    constructor(
        id?: number,
        title?: string,
        employeeId?: number,
        startTime?: number,
        endTime?: number

    ) {
        this.id = id;
        this.title = title;
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
