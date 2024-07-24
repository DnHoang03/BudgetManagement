import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { UserService } from '../service/user.service';

export const authGuard: CanActivateFn = (route, state) => {
  inject(UserService).getUserByUsername('hoang123').subscribe()
  return true;
};
