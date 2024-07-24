import { Component, OnInit } from '@angular/core';
import { Transaction } from '../../../model/transaction';
import { ActivatedRoute, Router } from '@angular/router';
import { TransactionsService } from '../../../service/transactions.service';
import { MatDialog } from '@angular/material/dialog';
import { DeleteComponent } from '../../../share/dialog/delete/delete.component';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrl: './detail.component.css'
})
export class DetailComponent implements OnInit {
  
  transaction!:Transaction;
  constructor(
  private activatedRoute:ActivatedRoute,
  private transactionService:TransactionsService,
  private dialog:MatDialog,
  private router:Router
  ) {

  }
  ngOnInit(): void {
    this.activatedRoute.params.subscribe(params=>{
      if(params["id"]){
        this.transactionService.getTransactionById(params["id"]).subscribe(
          transaction => {
            this.transaction = transaction;
            console.log(transaction)
          }
        )
      }
    })
  }

  deleteTransaction() {
    const dialogRef = this.dialog.open(DeleteComponent);
    dialogRef.afterClosed().subscribe(result => {
      if(result === 'confirm') {
        this.transactionService.deleteTransaction(this.transaction.id!).subscribe();
        this.router.navigate(['/home']);
      }
    })
  }
}
