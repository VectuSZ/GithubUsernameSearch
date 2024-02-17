
# GithubRepositoryApplicaton

GithubRepositoryApplication is a spring boot project, which lists all the entered GitHub user repositories, with its branches and last commit in sha model.

## Usage

For example in Postman
if you set up your server on localhost:

1.Create a new request with type GET.
2.Enter URL for your endpoint. In this case, it will be: http://localhost:8080/repositories/{username}, but replace '{username}' with username you want to list repositoreis.
3.In the Headers section, make sure you have the required headers, such as Accept: application/json.
4.Send a request.

