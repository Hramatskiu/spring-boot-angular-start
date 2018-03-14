import { Injectable } from '@angular/core';
import { Subject } from 'rxjs/Subject';

@Injectable()
export class ErrorReportingService {
  private errorMessage = new Subject<string>();
  errorMessage$ = this.errorMessage.asObservable();

  constructor() {  }

  reportError( errorMessage: string ) {
    this.errorMessage.next( errorMessage );
  }

  clearError() {
    this.errorMessage.next( "" );
  }
}
