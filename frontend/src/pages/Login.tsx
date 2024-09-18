import React, { useState } from 'react';
import { TextField, Button, Paper, Box, Typography } from '@mui/material';
import { login } from '../api/loginService';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');

    const handleLogin = async (e: React.FormEvent) => {
        e.preventDefault();

        try {
            const response = await login(username, password);
            const data = await response.json();

            if (response.ok) {
                localStorage.setItem('token', data.token);
                console.log('cest good '+data)
                //window.location.href = '/dashboard';
            } else {
                setErrorMessage(data.message || 'Login failed');
            }
        } catch (error) {
            setErrorMessage('An error occurred');
        }
    };

    return (
        <Box
            sx={{
                height: '100vh',
                display: 'flex',
                justifyContent: 'center',
                alignItems: 'center',
                backgroundColor: '#f0f4f8',
            }}
        >
            <Paper
                elevation={3}
                sx={{
                    padding: '2rem',
                    borderRadius: '8px',
                    borderColor: '#e0e0e0',
                    borderWidth: '1px',
                    borderStyle: 'solid',
                    maxWidth: '400px',
                    width: '100%',
                }}
            >
                <Typography variant="h4" component="h1" gutterBottom align="center">
                    Connexion
                </Typography>

                {errorMessage && (
                    <Typography color="error" align="center" sx={{ mb: 2 }}>
                        {errorMessage}
                    </Typography>
                )}

                <form onSubmit={handleLogin}>
                    <Box sx={{ mb: 2 }}>
                        <TextField
                            fullWidth
                            label="Nom d'utilisateur"
                            variant="outlined"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                        />
                    </Box>

                    <Box sx={{ mb: 2 }}>
                        <TextField
                            fullWidth
                            label="Mot de passe"
                            type="password"
                            variant="outlined"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                        />
                    </Box>

                    <Button
                        fullWidth
                        type="submit"
                        variant="contained"
                        sx={{
                            backgroundColor: '#1976d2',
                            '&:hover': {
                                backgroundColor: '#1976d2',
                                opacity: 0.9,
                            },
                        }}
                    >
                        Se connecter
                    </Button>
                </form>
            </Paper>
        </Box>
    );
};

export default Login;