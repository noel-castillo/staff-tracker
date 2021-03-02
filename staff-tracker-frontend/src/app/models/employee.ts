export class Employee {
    id: number;
    firstName: string;
    lastName: string;
    enabled: boolean;

    constructor(
        id?: number,
        firstName?: string,
        lastName?: string
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
