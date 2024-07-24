import { Transaction } from "./transaction";

export interface TransactionList {
    date?:Date,
    cost?:number,
    income?:number,
    transactions?:Transaction[]
}