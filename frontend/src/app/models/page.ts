import {Post} from "./post";

export interface Page {
  content: Post[],
  number: number,
  size: number,
  totalElements: number,
  pageable: {
    sort: {
      sorted: boolean,
      unsorted: boolean
    },
    offset: number,
    pageNumber: number,
    pageSize: number,
    paged: boolean,
    unpaged: boolean
  },
  last: boolean,
  totalPages: number,
  sort: {
    sorted: boolean,
    unsorted: true
  },
  first: boolean,
  numberOfElements: number;
}
