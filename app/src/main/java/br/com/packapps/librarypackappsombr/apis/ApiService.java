package br.com.packapps.librarypackappsombr.apis;



import java.util.List;
import java.util.Map;

import br.com.packapps.librarypackappsombr.apis.models.CardWrapper;
import br.com.packapps.librarypackappsombr.apis.models.HomeResponse;
import br.com.packapps.librarypackappsombr.apis.models.LineItemWrapper;
import br.com.packapps.librarypackappsombr.apis.models.OrderWrapper;
import br.com.packapps.librarypackappsombr.apis.models.OrdersResponse;
import br.com.packapps.librarypackappsombr.apis.models.PasswordResetResponse;
import br.com.packapps.librarypackappsombr.apis.models.ProductWrapper;
import br.com.packapps.librarypackappsombr.apis.models.ProductsByTaxonsResponse;
import br.com.packapps.librarypackappsombr.apis.models.ProductsResponse;
import br.com.packapps.librarypackappsombr.apis.models.ReviewWrapper;
import br.com.packapps.librarypackappsombr.apis.models.TaxonomiesResponse;
import br.com.packapps.librarypackappsombr.models.Address;
import br.com.packapps.librarypackappsombr.models.Card;
import br.com.packapps.librarypackappsombr.models.Config;
import br.com.packapps.librarypackappsombr.models.Country;
import br.com.packapps.librarypackappsombr.models.EmailWrapper;
import br.com.packapps.librarypackappsombr.models.Order;
import br.com.packapps.librarypackappsombr.models.PasswordChange;
import br.com.packapps.librarypackappsombr.models.Review;
import br.com.packapps.librarypackappsombr.models.Reviews;
import br.com.packapps.librarypackappsombr.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


public interface ApiService {

	//Get the list of all the products for the first page
	@GET("products")
	Call<ProductsResponse> getAllProducts();

	//get products in a particular category of the next pages in the trip list.
	@GET("search")
	Call<ProductsByTaxonsResponse> getProductsByTaxons(@Query("taxons[]") List<String> taxonNames);

	// Get the list of all the taxonomies
	@GET("taxonomies")
	Call<TaxonomiesResponse> getAllTaxonomies();

	// Get data for home page
	@GET("home")
	Call<HomeResponse> getHome();

	// User login
	@POST("users/sign_in")
	Call<User> login(@Body User user);

	// User Sign up
	@POST("users")
	Call<User> signup(@Body User user);

	// Password Reset
	@POST("password/reset")
	Call<PasswordResetResponse> resetPwd(@Body EmailWrapper emailWrapper);

	// Product detail
	@GET("products/{id}")
	Call<ProductWrapper> getProductById(@Path("id") int productId);

    // Product reviews
    @GET("product/reviews")
    Call<Reviews> getProductReviews(@Query("product_id") int product_id);

	// add a review
	@POST("product/reviews")
	Call<Review> addReviewForProduct(@Query("product_id") int product_id, @Query("token") String token, @Body ReviewWrapper reviewWrapper);

	//TODO : to be used when retrofit 2.1 is out
	// Product List with filters
	@GET("products")
	Call<ProductsResponse> getProductsByFilter(@QueryMap(encoded = true) Map<String, String> map);


	@PATCH("user/profiles/{token}")
	Call<User> editUser(@Path("token") String token, @Body User user);

	@PATCH("password/change")
	Call<User> changePwd(@Body PasswordChange passwordChange);

	@GET("user/addresses")
	Call<List<Address>> getUserAddresses(@Query("token") String token);

	@POST("user/addresses")
	Call<Address> createAddress(@Query("token") String token, @Body Address address);

	@PUT("user/addresses/{id}")
	Call<Address> updateAddress(@Path("id") int addressId, @Query("token") String token, @Body Address address);

	@DELETE("user/addresses/{id}")
	Call<Address> removeAddress(@Path("id") int addressId, @Query("token") String token);

	// --------------- Checkout flow ----------------
	// get current order if any
	@GET("orders/current")
	Call<Order> getCurrentOrder(@Query("token") String token);

	// create order in case current order does not exist
	@POST("orders")
	Call<Order> createOrder(@Query("token") String token, @Body OrderWrapper orderWrapper);

	// get order by id
	@GET("orders/{id}")
	Call<Order> getOrderById(@Path("id") String orderId, @Query("token") String token);

	// on add to cart if product already in cart then edit quantity of a product in cart
	@PATCH("orders/{orderId}/line_items/{id}")
	Call<Order> editQuantity(@Path("orderId") String orderId, @Path("id") int id, @Query("token") String token, @Body LineItemWrapper lineItemWrapper);

	// remove a product from cart
	@DELETE("orders/{orderId}/line_items/{id}")
	Call<Order> removeProduct(@Path("orderId") String orderId, @Path("id") int id, @Query("token") String token);

	// add to cart if not already in cart
	@POST("orders/{orderId}/line_items")
	Call<Order> addToCart(@Path("orderId") String orderId, @Query("token") String token, @Body LineItemWrapper lineItemWrapper);

	// add address for an order
	@PUT("checkouts/{orderId}")
	Call<Order> addAddressForOrder(@Path("orderId") String orderId, @Query("token") String token, @Body OrderWrapper orderWrapper);

	// add payment method for an order
	@POST("checkouts/{orderId}/payments")
	Call<Order> addPaymentForOrder(@Path("orderId") String orderId, @Query("token") String token/*, @Body PaymentWrapper paymentWrapper*/);

	// move to next state
	@PUT("checkouts/{orderId}/next")
	Call<Order> moveToNextState(@Path("orderId") String orderId, @Query("token") String token);

	// move to next state
	@PUT("checkouts/{orderId}")
	Call<Order> moveToConfirmState(@Path("orderId") String orderId, @Query("token") String token, @Body OrderWrapper orderWrapper);

	// move back a state
	@PUT("checkouts/{orderId}/back")
	Call<Order> moveBackAState(@Path("orderId") String orderId, @Query("token") String token);

	// ---------------------------------------------------

	@GET("users/credit_cards")
	Call<List<Card>> getUserCards(@Query("token") String token);

	@POST("users/credit_cards")
	Call<Card> createCard(@Query("token") String token, @Body CardWrapper cardWrapper);

	@DELETE("users/credit_cards/{id}")
	Call<Card> removeCard(@Path("id") int cardId, @Query("token") String token);

	// get my completed orders
	@GET("orders/mine")
	Call<OrdersResponse> getMyOrders(@Query("token") String token);

	// get config
	@GET("config")
	Call<Config> getConfig();

    // get states
    @GET("config/states")
    Call<Country> getStates();
}
