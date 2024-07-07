import { Component, inject } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrl: './delete.component.css'
})
export class DeleteComponent {
  readonly dialogRef = inject(MatDialogRef<DeleteComponent>);

  deleteItem() {
    this.dialogRef.close('confirm')
  }

  close() {
    this.dialogRef.close('refuse')
  }
}
