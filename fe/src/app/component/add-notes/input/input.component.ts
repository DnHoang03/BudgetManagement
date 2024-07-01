import { Component } from '@angular/core';
import { Icon } from '../../../model/icon';
import { Note } from '../../../model/note';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrl: './input.component.css'
})
export class InputComponent {
  numbers:(number|string)[] = [7, 8, 9, 'date', 4, 5, 6, '+', 1, 2, 3, '-', ',', 0, 'x', 'ok']
  current_value:string = '';
  pre_value:string = '';
  cal:string = '';
  dot:boolean = false;
  notes!:Note;
  evt_handle(num:(number|string)) {
    if(typeof num === 'number') {
      if(this.dot) {
        if(this.current_value.length-this.current_value.indexOf('.') == 3) {
          this.current_value = this.current_value.substring(0, this.current_value.length-1);
          this.current_value += String(num);
        } else {
          this.current_value += String(num);
        }
      } else {
        if(this.current_value.length>= 10) {
          this.current_value = this.current_value.substring(0, this.current_value.length-1);
        }
        this.current_value += String(num);
      }
    } else if(num == ',') {
      if(this.dot == false) {
        this.dot = true;
        this.current_value += '.';
      }
    } else if(num == '+') {
      if(this.cal == '+') {
        this.current_value = String(Number(this.pre_value)+Number(this.current_value));
        this.pre_value = this.current_value;
        this.current_value = '';
      } else if(this.cal == '-') {
        this.current_value = String(Number(this.pre_value)-Number(this.current_value));
        this.pre_value = this.current_value;
        this.current_value = '';
        this.cal = '+';
      } else {
        this.cal = '+';
        this.pre_value = this.current_value;
        this.current_value = '';
      }
    } else if(num == '-') {
      if(this.cal == '+') {
        this.current_value = String(Number(this.pre_value)+Number(this.current_value));
        this.pre_value = this.current_value;
        this.current_value = '';
        this.cal = '-';
      } else if(this.cal == '-') {
        this.current_value = String(Number(this.pre_value)-Number(this.current_value));
        this.pre_value = this.current_value;
        this.current_value = '';
      } else {
        this.cal = '-';
        this.pre_value = this.current_value;
        this.current_value = '';
      }
    } else if(num == 'ok') {
      if(this.cal == '+') {
        this.current_value = String(Number(this.pre_value)+Number(this.current_value));
        this.pre_value = '';
      } else if(this.cal == '-') {
        this.current_value = String(Number(this.pre_value)-Number(this.current_value));
        this.pre_value = '';
      }
      this.cal = '';
    } else if(num == 'x') {
      if(this.current_value.length) {
        this.current_value = this.current_value.substring(0, this.current_value.length-1);
      } else {
        if(this.pre_value.length) {
          this.current_value = this.pre_value;
          this.pre_value = '';
        }
        if(this.cal != '') {
          this.cal = '';
        }
      }
    }
    if(this.current_value.length-this.current_value.indexOf('.') > 3) {
      this.current_value = String(Math.round(Number(this.current_value)*100)/100);
    }
    if(this.current_value.indexOf('.') == -1) this.dot = false;
  }
}
