import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ApiendpointSharedModule } from 'app/shared/shared.module';
import { ApiEndpointComponent } from './api-endpoint.component';
import { ApiEndpointDetailComponent } from './api-endpoint-detail.component';
import { ApiEndpointUpdateComponent } from './api-endpoint-update.component';
import { ApiEndpointDeleteDialogComponent } from './api-endpoint-delete-dialog.component';
import { apiEndpointRoute } from './api-endpoint.route';

@NgModule({
  imports: [ApiendpointSharedModule, RouterModule.forChild(apiEndpointRoute)],
  declarations: [ApiEndpointComponent, ApiEndpointDetailComponent, ApiEndpointUpdateComponent, ApiEndpointDeleteDialogComponent],
  entryComponents: [ApiEndpointDeleteDialogComponent],
})
export class ApiendpointApiEndpointModule {}
