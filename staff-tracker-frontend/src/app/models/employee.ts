import { Position } from 'src/app/models/position';
export class Employee {
    id: number;
    firstName: string;
    lastName: string;
    email: string;
    phone: string;
    address: string;
    enabled: boolean;
    positions: Position[];

    constructor(
        id?: number,
        firstName?: string,
        lastName?: string,
        email?: string,
        phone?: string,
        address?: string,
        enabled?: boolean
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.enabled = enabled;
    }
}
