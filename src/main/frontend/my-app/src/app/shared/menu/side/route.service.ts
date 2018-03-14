import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable()
export class RouteService {
  constructor( private router: Router ) {  }

  routeToHealthCheck( clusterName: string ) {
    if ( this.router.url.indexOf( "cluster/" + clusterName ) == -1 ) {
      this.router.onSameUrlNavigation = 'ignore';
    }
    else {
      this.router.routeReuseStrategy.shouldReuseRoute = () => false;
      this.router.onSameUrlNavigation = 'reload';
    }

    this.router.navigate( ['/cluster/' + clusterName] );
  }
}
