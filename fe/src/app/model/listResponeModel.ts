import { ResponeModel } from "./responeModel";

export interface ListResponeModel<T> extends ResponeModel {
    data:T[]
}