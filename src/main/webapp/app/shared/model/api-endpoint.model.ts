import { ApiEndpointVendor } from 'app/shared/model/enumerations/api-endpoint-vendor.model';

export interface IApiEndpoint {
  id?: number;
  vendor?: ApiEndpointVendor;
  type?: string;
  enabled?: boolean;
  rate?: number;
  url?: string;
}

export class ApiEndpoint implements IApiEndpoint {
  constructor(
    public id?: number,
    public vendor?: ApiEndpointVendor,
    public type?: string,
    public enabled?: boolean,
    public rate?: number,
    public url?: string
  ) {
    this.enabled = this.enabled || false;
  }
}
