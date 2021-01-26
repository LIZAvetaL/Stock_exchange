import { Broker } from "./broker";

export class Bid {
    id: number;
    bidNumber: string;
    issuer: string;
    amount: number;
    minPrice: number;
    maxPrice: number;
    priority: string;
    status: string;
    creationDate: string;
    dueDate: string;
    broker: string;
}
