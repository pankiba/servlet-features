# study-servlets
Study features of Servlets ! Happy Learning !!

### Asynchronous Servlet

Async Servlet help us to deal with thread starvation problem with long running threads

  - @WebServlet annotation with asyncSupported value as true.
  - Setting of thread pool using Executors framework which will be used in delegation of actual work to another thread/s
  - We need to get instance of AsyncContext through ServletRequest.startAsync() method. AsyncContext provides methods to get the ServletRequest and ServletResponse object references. It also provides method to forward the request to another resource using dispatch() method.
  -
