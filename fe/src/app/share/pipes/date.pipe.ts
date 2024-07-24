import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'date'
})
export class DatePipe implements PipeTransform {

  transform(date:Date): String {
    const newDate = new Date(date.toString())
    // console.log(date.getDate())
    return newDate.getDate() + ' thg ' + (newDate.getMonth()+1) + ','+(newDate.getFullYear())
  }

}
