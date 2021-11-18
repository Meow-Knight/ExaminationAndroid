package com.example.tcpexamination;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tcpexamination.fragments.HistoryFragment;
import com.example.tcpexamination.fragments.ListExaminationFragment;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseUser currentUser;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    private CircleImageView ivAvatar;
    private TextView tvUsername;
    private TextView tvEmail;

    private DrawerLayout drawerLayout;
    private ImageView ivMenu;
    private NavigationView navigationView;
    private View headerView;

    public ListExaminationFragment listExaminationFragment;
    public HistoryFragment addScheduleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupGoogleSignIn();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        listExaminationFragment = new ListExaminationFragment();
        addScheduleFragment = new HistoryFragment();

        if (currentUser == null) {
            finish();
        }

        mapComponents();
        mapData();
        initEvents();
    }

    private void setupGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.my_default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void initEvents() {
        ivMenu.setOnClickListener(view -> drawerLayout.openDrawer(GravityCompat.START));
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void mapData() {
        tvUsername.setText(currentUser.getDisplayName());
        tvEmail.setText(currentUser.getEmail());
        Picasso.get()
                .load(currentUser.getPhotoUrl().toString())
                .into(ivAvatar);
    }

    private void mapComponents() {
        drawerLayout = findViewById(R.id.drawerlayout);
        ivMenu = findViewById(R.id.iv_menu);
        navigationView = findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);
        headerView = navigationView.getHeaderView(0);

        ivAvatar = headerView.findViewById(R.id.iv_avatar);
        tvUsername = headerView.findViewById(R.id.tv_name);
        tvEmail = headerView.findViewById(R.id.tv_mail);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case R.id.it_examination:
            case R.id.it_history:
                setMyFragment(itemId);
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.item_version:
                Toast.makeText(MainActivity.this, "item_version", Toast.LENGTH_SHORT).show();
                break;
            case R.id.item_logout:
                mAuth.signOut();
                mGoogleSignInClient.signOut();
                finish();
                break;
            default:
                Toast.makeText(MainActivity.this, "uh", Toast.LENGTH_SHORT).show();
        }

        return false;
    }

    public void setMyFragment(int fragmentId) {
        //get fragment transaction
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (fragmentId) {
            case R.id.it_examination:
                fragmentTransaction.replace(R.id.nav_host_fragment, listExaminationFragment, ListExaminationFragment.FRAGMENT_TAG);
                break;
            case R.id.it_history:
                fragmentTransaction.replace(R.id.nav_host_fragment, addScheduleFragment, HistoryFragment.FRAGMENT_TAG);
                break;
        }

        fragmentTransaction.commit();
        getSupportFragmentManager().executePendingTransactions();
    }
}