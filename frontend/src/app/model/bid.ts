export class Bid {
  id: number;
  bidNumber: string;
  issuer: string;
  amount: number;
  minPrice: number;
  maxPrice: number;
  priority: string;
  status: string;
  creationDate: Date;
  dueDate: Date;
  broker: string;
  client: string;
}
