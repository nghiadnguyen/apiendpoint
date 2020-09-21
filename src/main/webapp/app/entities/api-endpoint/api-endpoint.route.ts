import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IApiEndpoint, ApiEndpoint } from 'app/shared/model/api-endpoint.model';
import { ApiEndpointService } from './api-endpoint.service';
import { ApiEndpointComponent } from './api-endpoint.component';
import { ApiEndpointDetailComponent } from './api-endpoint-detail.component';
import { ApiEndpointUpdateComponent } from './api-endpoint-update.component';

@Injectable({ providedIn: 'root' })
export class ApiEndpointResolve implements Resolve<IApiEndpoint> {
  constructor(private service: ApiEndpointService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IApiEndpoint> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((apiEndpoint: HttpResponse<ApiEndpoint>) => {
          if (apiEndpoint.body) {
            return of(apiEndpoint.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ApiEndpoint());
  }
}

export const apiEndpointRoute: Routes = [
  {
    path: '',
    component: ApiEndpointComponent,
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'apiendpointApp.apiEndpoint.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ApiEndpointDetailComponent,
    resolve: {
      apiEndpoint: ApiEndpointResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'apiendpointApp.apiEndpoint.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ApiEndpointUpdateComponent,
    resolve: {
      apiEndpoint: ApiEndpointResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'apiendpointApp.apiEndpoint.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ApiEndpointUpdateComponent,
    resolve: {
      apiEndpoint: ApiEndpointResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'apiendpointApp.apiEndpoint.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
