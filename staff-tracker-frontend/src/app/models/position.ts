export class Position {
    id: number;
    title: string;
    status: string;
    days: string;
    employeeId: number;
    startTime: string;
    endTime: string;
    enabled: boolean;

    constructor(
        id?: number,
        title?: string,
        status?: string,
        days?: string,
        employeeId?: number,
        startTime?: string,
        endTime?: string

    ) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.days = days;
        this.employeeId = employeeId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
