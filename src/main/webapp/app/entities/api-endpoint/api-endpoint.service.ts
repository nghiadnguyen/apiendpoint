import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IApiEndpoint } from 'app/shared/model/api-endpoint.model';

type EntityResponseType = HttpResponse<IApiEndpoint>;
type EntityArrayResponseType = HttpResponse<IApiEndpoint[]>;

@Injectable({ providedIn: 'root' })
export class ApiEndpointService {
  public resourceUrl = SERVER_API_URL + 'api/api-endpoints';

  constructor(protected http: HttpClient) {}

  create(apiEndpoint: IApiEndpoint): Observable<EntityResponseType> {
    return this.http.post<IApiEndpoint>(this.resourceUrl, apiEndpoint, { observe: 'response' });
  }

  update(apiEndpoint: IApiEndpoint): Observable<EntityResponseType> {
    return this.http.put<IApiEndpoint>(this.resourceUrl, apiEndpoint, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IApiEndpoint>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IApiEndpoint[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
