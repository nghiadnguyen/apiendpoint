import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'api-endpoint',
        loadChildren: () => import('./api-endpoint/api-endpoint.module').then(m => m.ApiendpointApiEndpointModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class ApiendpointEntityModule {}
